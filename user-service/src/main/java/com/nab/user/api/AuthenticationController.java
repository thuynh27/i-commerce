package com.nab.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nab.user.dto.UserDTO;
import com.nab.user.service.UserSerivce;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
	
	@Autowired
	private UserSerivce userService;
	
	@ApiOperation(value = "Generate Token for Facebook User", nickname = "getUserDetails", notes = "Get Authentication Info for authenticaiton checking", response = String.class, authorizations = {})
	@ApiResponses(value = @ApiResponse(code = 200, message = "OK", response = String.class))
	@PostMapping
	public ResponseEntity<String> getUserDetails(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(userService.authenticationOauth2User(userDTO));
	}
}
