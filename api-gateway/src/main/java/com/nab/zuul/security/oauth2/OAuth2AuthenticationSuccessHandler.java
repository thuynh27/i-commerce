package com.nab.zuul.security.oauth2;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nab.zuul.model.Role;
import com.nab.zuul.model.UserAuthority;
import com.nab.zuul.repository.UserRepository;
import com.nab.zuul.security.JwtTokenUtil;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;

    @Autowired
    OAuth2AuthenticationSuccessHandler(){
    	
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	this.generateToken(response , authentication); 
    	super.onAuthenticationSuccess(request, response, authentication);
    }

	private void generateToken(HttpServletResponse response, Authentication authentication) {
		String token = authentication(authentication);
		if(StringUtils.isNotEmpty(token)) {
			response.addHeader("Authorization","Bearer " +token);
		}
	}
	
	public String authentication(Authentication authentication) {
		//1.Fetching User Info
		if(Objects.nonNull(authentication) && authentication.getPrincipal() instanceof OAuth2User) {
			OAuth2User user = (OAuth2User) authentication.getPrincipal();
			String userEmail = (String) user.getAttributes().get("email");
			final UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
			if (Objects.nonNull(userDetails)) {
				//update exist user
				updateExistUser(user);
			}else {
				storedNewUser(user);
			}
			return  jwtTokenUtil.generateToken(userEmail);
		}
		return StringUtils.EMPTY;
	}

	private UserAuthority storedNewUser(OAuth2User oAuth2User) {
		UserAuthority user = new UserAuthority();
		user.setName(oAuth2User.getName());
		user.setEmail((String) oAuth2User.getAttributes().get("email"));
		user.setRole(Role.FACEBOOK_USER);
		return userRepository.save(user);
	}

	private UserAuthority updateExistUser(OAuth2User oAuth2User) {
		UserAuthority user = new UserAuthority();
		user.setName(oAuth2User.getName());
		return userRepository.save(user);
	}
	
}
