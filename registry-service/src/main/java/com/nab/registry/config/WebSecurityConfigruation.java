package com.nab.registry.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfigruation extends WebSecurityConfigurerAdapter {
	
	
    /**
     * Authentication for login Registry Service
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .csrf()
	        .disable()
	        .authorizeRequests()
	        .anyRequest().authenticated()
	        .and()
	        .httpBasic();
    }

}
