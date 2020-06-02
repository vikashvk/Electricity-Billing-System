package com.ebs.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Poonamchand Sahu
 * 
 */
public class LoginRequest {
	@NotBlank(message = "Email cannot be blank")
	@Email
	private String email;

	@NotBlank(message = "Password cannot be blank")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
