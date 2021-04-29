package com.nab.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.nab.user.utils.Constants;

import lombok.Data;

@Data
public class UserDTO {

	@Size(max = 50)
    @Pattern(regexp = Constants.LOGIN_REGEX)
	private String userName;

    @Email
    @NotNull
	private String email;

	private String password;

	public UserDTO(
			@Size(max = 50)  @Pattern(regexp = Constants.LOGIN_REGEX) String userName,
			@NotNull @Email String email) {
		this.userName = userName;
		this.email = email;
	}
    
    
  
}
