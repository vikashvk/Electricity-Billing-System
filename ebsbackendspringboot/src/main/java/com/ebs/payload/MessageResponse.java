package com.ebs.payload;

/**
 * Used for Sending only a message in response
 * 
 * @author Poonamchand Sahu
 * 
 */
public class MessageResponse {
	private String message;

	private boolean success = true;

	public MessageResponse(String message) {
		this.message = message;
	}

	public MessageResponse(String message, boolean success) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
