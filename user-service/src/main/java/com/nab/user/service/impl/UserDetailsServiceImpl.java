package com.nab.user.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.security.auth.login.FailedLoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nab.user.converter.UserConverter;
import com.nab.user.dto.UserDTO;
import com.nab.user.model.Role;
import com.nab.user.model.UserAuthority;
import com.nab.user.repository.UserRepository;
import com.nab.user.security.JwtTokenUtil;
import com.nab.user.service.UserSerivce;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserSerivce {

	UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	private JwtTokenUtil jwtTokenUtil;

	private AuthenticationManager authenticationManager;

	public static final String USER_DISABLED = "USER_DISABLED";

	public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenUtil = jwtTokenUtil;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		UserAuthority user = userRepository.findByEmail(email).orElse(null);
		List<GrantedAuthority> authorities = Collections
				.singletonList(new SimpleGrantedAuthority(Role.ROLE_ADMIN.name()));
		if (user != null && user.getEmail() != null) {
			return new User(user.getEmail(), user.getPassword() != null ? user.getPassword() : user.getName(),
					authorities);
		}
		return null;
	}

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		UserAuthority user = UserConverter.getInstance().convertFromDto(userDTO);
		UserAuthority checkUserName = userRepository.findByEmail(user.getEmail()).orElse(null);
		;
		if (checkUserName == null) {
			user.setRole(Role.ROLE_ADMIN);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			UserAuthority userSaved = userRepository.save(user);
			return UserConverter.getInstance().convertFromEntity(userSaved);
		}
		return null;

	}

	@Override
	public String authentication(UserDTO userDTO) throws FailedLoginException {
		final UserDetails userDetails = loadUserByUsername(userDTO.getUserName());
		if (Objects.nonNull(userDetails)) {
			authenticate(userDTO.getUserName(), userDTO.getPassword());
			return jwtTokenUtil.generateToken(userDetails);
		}
		throw new FailedLoginException("user name or passwords failed");
	}

	private void authenticate(String username, String password) throws DisabledException, BadCredentialsException {
		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(auth);
		} catch (DisabledException e) {
			throw new DisabledException(USER_DISABLED, e);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(INVALID_CREDENTIALS, e);
		}
	}

	@Override
	@Transactional
	public String authenticationOauth2User(UserDTO userDTO) {
		UserAuthority user = userRepository.findByEmail(userDTO.getEmail()).orElse(null);
		if (Objects.nonNull(user) && Objects.isNull(user.getEmail())) {
			user = UserConverter.getInstance().convertFromDto(userDTO);
			userRepository.save(user);
		}
		List<GrantedAuthority> authorities = Collections
				.singletonList(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
		User userSercurity = new User(user.getEmail(), user.getPassword() != null ? user.getPassword() : user.getName(),
				authorities);
		return jwtTokenUtil.generateToken(userSercurity);
	}

}
