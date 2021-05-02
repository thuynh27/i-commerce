package com.nab.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nab.product.model.Role;
import com.nab.product.security.JwtAuthenticationEntryPoint;
import com.nab.product.security.JwtRequestFilter;

@EnableWebSecurity
public class WebSecurityConfigruation extends WebSecurityConfigurerAdapter {

	@Value("${gateway.ip}")
	private String gatewayIP;
	
	private JwtRequestFilter jwtRequestFilter;
	  
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	public WebSecurityConfigruation(JwtRequestFilter jwtRequestFilter,
			JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
		this.jwtRequestFilter = jwtRequestFilter;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
				.csrf()
				.disable()
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				.antMatchers("/product/**").permitAll()
				.antMatchers("/admin/**").hasAuthority(Role.ROLE_ADMIN.name())
				.anyRequest().authenticated() ;
		
		//JWT filter based on Authentication Filter 
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
