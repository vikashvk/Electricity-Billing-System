package com.ebs.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * Exception Handler class
 */
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
	/*
	 * Handles when an error occur in application
	 */
	@ExceptionHandler(EbsException.class)
	public final ResponseEntity<String> handleUserException(EbsException ex, WebRequest request) {
		return new ResponseEntity<String>(ex.getLocalizedMessage(), ex.getHttpStatus());
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<String> handleUserException(UsernameNotFoundException ex, WebRequest request) {
		return new ResponseEntity<String>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
	}

	/*
	 * handles all the validations for @Valid
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errors = "";
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errors += error.getDefaultMessage() + ". ";
		}
		return new ResponseEntity<Object>(errors, HttpStatus.EXPECTATION_FAILED);
	}

	/*
	 * Handles when server error is occured
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request) {
		String error = "Could not process your request at this time.";
		return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
