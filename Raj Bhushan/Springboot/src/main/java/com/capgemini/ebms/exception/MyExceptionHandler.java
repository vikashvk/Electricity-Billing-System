package com.capgemini.ebms.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capgemini.ebms.beans.ResponseModel;

/*
 * Exception Handler class
 */
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
	/*
	 * Handles when an error occur in application
	 */
	@ExceptionHandler(UserException.class)
	public final ResponseEntity<ResponseModel> handleUserException(UserException ex, WebRequest request) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getLocalizedMessage());
		ResponseModel response = new ResponseModel(false, null);
		response.setErrors(errors);
		return new ResponseEntity<ResponseModel>(response, ex.getHttpStatus());
	}

	/*
	 * handles all the validations for @Valid
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errors.add(error.getDefaultMessage());
		}
		ResponseModel response = new ResponseModel(false, null);
		response.setErrors(errors);
		return new ResponseEntity<Object>(response, HttpStatus.EXPECTATION_FAILED);
	}

	/*
	 * Handles when server error is occured
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseModel> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> errors = new ArrayList<>();
		errors.add("Could not process your request at this time.");
		ResponseModel response = new ResponseModel(false, null);
		response.setErrors(errors);
		return new ResponseEntity<ResponseModel>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
