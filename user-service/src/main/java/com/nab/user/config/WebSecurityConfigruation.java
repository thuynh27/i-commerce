package com.nab.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nab.user.service.impl.UserDetailsServiceImpl;


@EnableWebSecurity
public class WebSecurityConfigruation extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsService;
	
	@Value("${gateway.ip}")
	private String gatewayIP;

	@Autowired
	public WebSecurityConfigruation( UserDetailsServiceImpl userDetailsService ) {
		this.userDetailsService = userDetailsService;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Authentication for login with SSO combine with JWT
	 *
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/**").hasIpAddress(gatewayIP)
		.antMatchers("/users/**").permitAll()
		.antMatchers("/authentication").permitAll()
		.anyRequest().authenticated();
		http.headers().frameOptions().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
