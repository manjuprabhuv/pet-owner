package com.manju.ownersapi;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.manju.entities.OwnerEntity;
import com.manju.exceptions.ErrorGenerator;
import com.manju.exceptions.InvalidInputException;
import com.manju.exceptions.ResourceNotFoundException;
import com.manju.mapper.EntityMapper;
import com.manju.petowner.swagger.api.OwnersApi;
import com.manju.petowner.swagger.model.Error;
import com.manju.petowner.swagger.model.Owner;
import com.manju.repositories.OwnerRepository;

@CrossOrigin
@RestController
public class OwnersResource implements OwnersApi {

	@Autowired
	private OwnerRepository ownerRepository;

	@Override
	public ResponseEntity<List<Owner>> getOwners() {
		// TODO Auto-generated method stub
		List<Owner> owners = new ArrayList<Owner>();
		Iterable<OwnerEntity> ownersIterator = ownerRepository.findAll();
		ownersIterator.forEach((owner) -> {
			owners.add(EntityMapper.mapOwnerEntityToOwner(owner));
		});
		return new ResponseEntity<List<Owner>>(owners, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Owner> getOwnerbyownerId(@NotNull String ownerId) {
		// TODO Auto-generated method stub
		Long ownerIdLong = 0L;
		try {
			ownerIdLong = Long.parseLong(ownerId);
		} catch (Exception e) {
			Error error = ErrorGenerator.getError(10006, "Invalid ownerId",
					"PetId pass is invalid, please pass the right ownerId");
			throw new InvalidInputException(error);
		}
		OwnerEntity ownerEntity = null;
		try {
			ownerEntity = ownerRepository.findById(ownerIdLong).get();
		}catch(Exception e) {
			Error error = ErrorGenerator.getError(10007, "Owner resource not found", "Owner not found");
			throw new ResourceNotFoundException(error);
		}
		
		Owner owner = EntityMapper.mapOwnerEntityToOwner(ownerEntity);
		
		return new ResponseEntity<Owner>(owner, HttpStatus.OK);
	}

}
