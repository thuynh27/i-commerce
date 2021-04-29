package com.nab.zuul.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {

	@Size(max = 50)
	private String userName;

    @Email
	private String email;

	private String password;

	public UserDTO(
			@Size(max = 50)String userName,
			@NotNull @Email String email) {
		this.userName = userName;
		this.email = email;
	}
    
    
  
}
