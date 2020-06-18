package com.ebs.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Triggered when error occurs during OAuth
 * 
 * @author Poonamchand Sahu
 * 
 */
public class OAuth2AuthenticationProcessingException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
		super(msg, t);
	}

	public OAuth2AuthenticationProcessingException(String msg) {
		super(msg);
	}
}
