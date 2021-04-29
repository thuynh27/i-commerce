package com.nab.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.nab.user.utils.Constants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "user_authority")
public class UserAuthority extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	@Size(max = 50)
    @Pattern(regexp = Constants.LOGIN_REGEX)
	private String name;

	@Column(name = "email")
    @NotNull
    @Email
	private String email;
	
	@Column(name = "password")
	private String password;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;

	public UserAuthority(
			@Size(max = 50) @NotNull @Pattern(regexp = Constants.LOGIN_REGEX) String name,
			@NotNull @Email String email, @NotNull String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}	
}
