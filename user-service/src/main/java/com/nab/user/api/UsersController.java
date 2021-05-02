package com.nab.user.api;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nab.user.dto.UserDTO;
import com.nab.user.service.UserSerivce;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Users", produces = MediaType.APPLICATION_JSON_VALUE, tags = { "Users API" })
@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Server Error") })
@RestController
@RequestMapping("/admin")
public class UsersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
	@Autowired
	private Environment env;

	private UserSerivce userSerivce;

	@Autowired
	public UsersController(Environment env, UserSerivce userSerivce) {
		this.env = env;
		this.userSerivce = userSerivce;
	}

	@GetMapping("/checking")
	public String getStatus() {
		return "Working on port " + env.getProperty("local.server.port");
	}

	@ApiOperation(value = "Token Authentication generation ", nickname = "userLogin", notes = "Token Authentication generation ", response = UserDTO.class, authorizations = {})
	@ApiResponses(value = @ApiResponse(code = 200, message = "OK", response = UserDTO.class))
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody UserDTO userDTO, HttpServletResponse response) {
		try {
			final String token = userSerivce.authentication(userDTO);
			response.addHeader("Authorization", "Bearer " + token);
			return ResponseEntity.ok(userDTO);
		} catch (FailedLoginException e) {
			LOGGER.warn("Login Failed : ", e);
			return ResponseEntity.badRequest().body("Login Failed !");
		}
	}

	@ApiOperation(value = "Register New User", nickname = "registerUser", notes = "Register New User", response = UserDTO.class, authorizations = {})
	@ApiResponses(value = @ApiResponse(code = 200, message = "OK", response = UserDTO.class))
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
		UserDTO user = userSerivce.registerUser(userDTO);
		if (user != null) {
			return ResponseEntity.ok().body(user);
		}
		return ResponseEntity.badRequest().build();
	}
}
