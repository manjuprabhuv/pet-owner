package com.manju.exceptions;

import com.manju.petowner.swagger.model.Error;

public abstract class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Error error;

	public Error getError() {
		return error;
	}

	private void setError(Error error) {
		this.error = error;
	}

	public ApplicationException(Error error) {
		super(error.getMessage());
		this.setError(error);

	}

}
