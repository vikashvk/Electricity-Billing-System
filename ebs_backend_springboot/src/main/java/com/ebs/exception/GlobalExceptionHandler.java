package com.ebs.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ebs.payload.MessageResponse;
import com.stripe.exception.StripeException;

/*
 * Exception Handler class
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
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
		MessageResponse response = new MessageResponse(errors, false);
		return new ResponseEntity<Object>(response, HttpStatus.EXPECTATION_FAILED);
	}

	

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		MessageResponse response = new MessageResponse(ex.getLocalizedMessage(), false);
		return new ResponseEntity<MessageResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(OAuth2AuthenticationProcessingException.class)
	public final ResponseEntity<?> handleOAuth2AuthenticationProcessingException(
			OAuth2AuthenticationProcessingException ex, WebRequest request) {
		MessageResponse response = new MessageResponse(ex.getLocalizedMessage(), false);
		return new ResponseEntity<MessageResponse>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public final ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
		MessageResponse response = new MessageResponse("Invalid email or password.", false);
		return new ResponseEntity<MessageResponse>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		MessageResponse response = new MessageResponse("Please login first.", false);
		return new ResponseEntity<MessageResponse>(response, HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<?> handleBadRequestException(BadRequestException ex, WebRequest request) {
		MessageResponse response = new MessageResponse(ex.getLocalizedMessage(), false);
		return new ResponseEntity<MessageResponse>(response, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(StripeException.class)
	public final ResponseEntity<?> handleStripeException(StripeException ex, WebRequest request) {
		MessageResponse response = new MessageResponse(ex.getLocalizedMessage(), false);
		return new ResponseEntity<MessageResponse>(response, HttpStatus.BAD_REQUEST);
	}
	/*
	 * Handles when any other exception is occured
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
		ex.printStackTrace();
		String message = "Could not process your request at this time.";
		MessageResponse response = new MessageResponse(message, false);
		return new ResponseEntity<MessageResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
