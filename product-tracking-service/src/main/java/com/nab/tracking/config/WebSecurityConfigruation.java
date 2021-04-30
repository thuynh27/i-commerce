package com.nab.tracking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfigruation extends WebSecurityConfigurerAdapter {

	@Value("${gateway.ip}")
	private String gatewayIP;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/**").hasIpAddress(gatewayIP)
			.and()
			.headers().frameOptions().disable();
	}

}
