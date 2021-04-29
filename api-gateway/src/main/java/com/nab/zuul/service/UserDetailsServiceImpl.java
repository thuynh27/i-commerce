package com.nab.zuul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nab.zuul.model.UserAuthority;
import com.nab.zuul.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		UserAuthority user = userRepository.findByEmail(email).orElse(null);
		if (user != null) {
			return new User(user.getName(), user.getPassword(), Boolean.TRUE, Boolean.TRUE, Boolean.TRUE,
					Boolean.TRUE, AuthorityUtils.NO_AUTHORITIES);
		}
		return null;
	}
}
