package com.manju.exceptions;

import com.manju.petowner.swagger.model.Error;

public class ErrorGenerator {

	public static Error getError(Integer code, String details, String message) {
		Error error = new Error();
		error.setCode(code);
		error.setDetails(details);
		error.setMessage(message);
		return error;
	}

}
