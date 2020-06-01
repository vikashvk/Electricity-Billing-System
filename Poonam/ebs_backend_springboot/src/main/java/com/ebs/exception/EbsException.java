package com.ebs.exception;

import org.springframework.http.HttpStatus;

public class EbsException extends Exception {

	/**
	 * Thrown when an error occur
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public EbsException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public EbsException(String message) {
		super(message);
		httpStatus = HttpStatus.NOT_FOUND;
	}

}
