package com.capgemini.ebms.util;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capgemini.ebms.exception.UserCreationException;

@ControllerAdvice
	public class LibraryExceptionHandler extends ResponseEntityExceptionHandler {

		@ExceptionHandler(UserCreationException.class)
		public final ResponseEntity<UserExceptionResponse> handleWrongUserInputException(UserCreationException ex,
				WebRequest req) {

			UserExceptionResponse exceptionResponse = new UserExceptionResponse(new Date(), ex.getMessage(),

					req.getDescription(false), HttpStatus.BAD_REQUEST.getReasonPhrase());

			return new ResponseEntity<UserExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);

		}
	}


