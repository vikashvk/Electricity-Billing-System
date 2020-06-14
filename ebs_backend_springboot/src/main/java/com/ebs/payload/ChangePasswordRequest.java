package com.ebs.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Used for changing password
 * 
 * @author Poonamchand Sahu
 *
 */
public class ChangePasswordRequest {
	@NotBlank(message = "Current password cannot be blank")
	private String currentPassword;
	@NotBlank(message = "New password cannot be blank")
	@Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}", message = "Password should contain atleast one uppercase , one lower case, one digit and should be of 6 or more character.")
	private String newPassword;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
