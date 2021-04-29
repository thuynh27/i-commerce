package com.nab.user.service;

import javax.security.auth.login.FailedLoginException;

import org.springframework.stereotype.Service;

import com.nab.user.dto.UserDTO;

@Service
public interface UserSerivce {

	UserDTO registerUser(final UserDTO userDTO);

	String authentication(final UserDTO userDTO) throws FailedLoginException;
	
	String getStatusFromManagementService();
	
	String authenticationOauth2User(final UserDTO userDTO);
	
}
