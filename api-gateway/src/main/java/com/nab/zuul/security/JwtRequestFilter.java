package com.nab.zuul.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Value("${authorization.token.header.name}")
	private String headerAuthorization;
	
	@Value("${authorization.token.header.prefix}")
	private String headerPrefix;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader(headerAuthorization);
		if (requestTokenHeader != null && requestTokenHeader.startsWith(headerPrefix)) {
			String jwtToken = requestTokenHeader.substring(7);
			try {
				 UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(jwtToken);
				 SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (IllegalArgumentException e) {
				LOGGER.warn("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				LOGGER.warn("JWT Token has expired");
			}
		} else {
			LOGGER.warn("JWT Token does not begin with Bearer String");
		}
		chain.doFilter(request, response);
	}
}
