package com.ebs.payload;

import javax.validation.constraints.NotBlank;

public class ChangePasswordRequest {
	@NotBlank(message = "Current password cannot be blank")
	private String currentPassword;
	@NotBlank(message = "New password cannot be blank")
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
