package com.manju.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.manju.petowner.swagger.model.Error;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public InvalidInputException(Error error) {
		super(error);
		// TODO Auto-generated constructor stub
	}
	

}
