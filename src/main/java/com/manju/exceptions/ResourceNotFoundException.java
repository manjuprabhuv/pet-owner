package com.manju.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.manju.petowner.swagger.model.Error;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;

	public ResourceNotFoundException(Error error) {

		super(error);

	}

}
