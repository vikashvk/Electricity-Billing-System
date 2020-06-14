package com.ebs.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class PasswordResetRequest {
	@NotBlank(message = "Token cannot be blank")
	private String token;
	@NotBlank(message = "New password cannot be blank")
	@Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}",message = "Password should contain atleast one uppercase , one lower case, one digit and should be of 6 or more character.")
	private String newPassword;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
