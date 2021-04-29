package com.nab.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nab.zuul.security.JwtAuthenticationEntryPoint;
import com.nab.zuul.security.JwtRequestFilter;
import com.nab.zuul.service.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class WebSecurityConfigruation extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsService;
	
	private JwtRequestFilter jwtRequestFilter;
  
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	public WebSecurityConfigruation(UserDetailsServiceImpl userDetailsService 
			, JwtRequestFilter jwtRequestFilter 
			, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint ) {
		this.userDetailsService = userDetailsService;
		this.jwtRequestFilter = jwtRequestFilter;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/users-ws/admin/**").permitAll()
				.antMatchers("/users-ws/h2-console/**", "/users-ws/users", "/users-ws/users/login",
						"/users-ws/actuator/*", "/users-ws/users/register", "/users-ws/users/authentication/**")
				.permitAll()
				.anyRequest().authenticated()
				.and()
					.oauth2Login()// enable OAuth2
//					.successHandler(oAuth2AuthenticationSuccessHandler)
//		            .failureHandler(oAuth2AuthenticationFailureHandler)
					.defaultSuccessUrl("/gateway/oauth2LoginSuccess")
				.and()
					.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and().csrf().disable(); // disable CSRF
		
		//JWT filter based on Authentication Filter 
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
