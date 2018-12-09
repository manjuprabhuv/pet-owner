package com.manju.petapi;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

import com.manju.exceptions.ErrorGenerator;
import com.manju.exceptions.InvalidInputException;
import com.manju.petowner.swagger.model.Pet;
import com.manju.petowner.swagger.model.Error;

public class RequestValidator {

	public static void petRequestValidator(Pet pet) {
		try {
			if (StringUtils.isEmpty(pet.getBirthday()) || StringUtils.isEmpty(pet.getName())
					|| StringUtils.isEmpty(pet.getOwnerId())) {
				throw new Exception("Mandatory input not passed");
			}
			if(pet.getBirthday().isAfter(LocalDate.now())|| pet.getBirthday().isBefore(LocalDate.of(LocalDate.now().getYear()-100, 01, 01))) {
				throw new Exception("Invalid Date");
			}
		} catch (Exception e) {
			Error error = ErrorGenerator.getError(10009, e.getMessage(), e.getMessage());
			throw new InvalidInputException(error);

		}
	}

}
