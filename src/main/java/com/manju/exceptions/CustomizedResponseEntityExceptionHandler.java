package com.manju.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.manju.petowner.swagger.model.Error;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Error> handleAllExceptions(Exception ex, WebRequest request) {
		Error error = new Error();
		error.setCode(10001);
		error.setMessage("Generic Server Error");
		error.setDetails("Generic Server Error");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Error> handleUserNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		return new ResponseEntity<>(ex.getError(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidInputException.class)
	public final ResponseEntity<Error> handleUserNotFoundException(InvalidInputException ex, WebRequest request) {

		return new ResponseEntity<>(ex.getError(), HttpStatus.BAD_REQUEST);
	}
}