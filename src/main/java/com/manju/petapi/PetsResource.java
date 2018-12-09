package com.manju.petapi;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.manju.entities.PetEntity;
import com.manju.exceptions.ErrorGenerator;
import com.manju.exceptions.InvalidInputException;
import com.manju.exceptions.ResourceNotFoundException;
import com.manju.mapper.EntityMapper;
import com.manju.petowner.swagger.api.PetsApi;
import com.manju.petowner.swagger.model.Error;
import com.manju.petowner.swagger.model.Pet;
import com.manju.repositories.PetRepository;

@RestController
public class PetsResource implements PetsApi {

	@Autowired
	private PetRepository petRepository;

	@Override
	public ResponseEntity<List<Pet>> getPets() {
		List<Pet> pets = new ArrayList<Pet>();
		Iterable<PetEntity> petIterator = petRepository.findAll();
		petIterator.forEach((pet) -> {
			pets.add(EntityMapper.mapPetEntityToPet(pet));

		});
		return new ResponseEntity<List<Pet>>(pets, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Pet> getPetbyPetId(@NotNull String petId) {
		// TODO Auto-generated method stub
		Long petIdLong = 0L;
		try {
			petIdLong = Long.parseLong(petId);
		} catch (Exception e) {
			Error error = ErrorGenerator.getError(10004, "Invalid petId",
					"PetId pass is invalid, please pass the right petId");
			throw new InvalidInputException(error);
		}
		PetEntity petEntity = null;
		try {
			petEntity = petRepository.findById(petIdLong).get();
		}catch(Exception e) {
			Error error = ErrorGenerator.getError(10002, "Pet resource not found", "Pet not found");
			throw new ResourceNotFoundException(error);
		}
		
		Pet pet = EntityMapper.mapPetEntityToPet(petEntity);		
		return new ResponseEntity<Pet>(pet, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> addPet(@Valid Pet pet) {
		// TODO Auto-generated method stub
		RequestValidator.petRequestValidator(pet);
		PetEntity petEntity = EntityMapper.mapPetToPetEntity(pet);
		petRepository.save(petEntity);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
