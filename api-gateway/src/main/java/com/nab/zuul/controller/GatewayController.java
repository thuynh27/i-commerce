package com.nab.zuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nab.zuul.dto.UserDTO;
import com.nab.zuul.service.feign.UserServiceClient;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

	private UserServiceClient userServiceClient;

	@Autowired
	public GatewayController(UserServiceClient userServiceClient) {
		this.userServiceClient = userServiceClient;
	}

	@GetMapping("/oauth2LoginSuccess")
	public String getOauth2LoginInfo(@AuthenticationPrincipal OAuth2AuthenticationToken authenticationToken) {
		// 1.Fetching User Info
		OAuth2User user = authenticationToken.getPrincipal();
		UserDTO userDTO = new UserDTO(user.getName(), (String) user.getAttributes().get("email"));
		return userServiceClient.getUserDetails(userDTO);
	}
}
