package com.manju.mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.manju.entities.OwnerEntity;
import com.manju.entities.PetEntity;
import com.manju.petowner.swagger.model.Owner;
import com.manju.petowner.swagger.model.Pet;
import com.manju.repositories.OwnerRepository;
import com.manju.repositories.PetRepository;

public class EntityMapper {
	
	/*@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;*/

	public static Pet mapPetEntityToPet(PetEntity petEntity) {
		Pet pet = new Pet();
		pet.setId(petEntity.getPetId());
		pet.setName(petEntity.getName());
		pet.setBirthday(petEntity.getBirthDate().toLocalDate());
		pet.setOwnerId(petEntity.getOwner().getOwnerId());
		return pet;

	}
	
	public static PetEntity mapPetToPetEntity(Pet pet){
		PetEntity petEntity  = new PetEntity();
		petEntity.setPetId(pet.getId());
		petEntity.setName(pet.getName());
		petEntity.setBirthDate(Date.valueOf(pet.getBirthday()));
		if(pet.getOwnerId()!=null) {
			OwnerEntity owner =  new OwnerEntity();
			owner.setOwnerId(pet.getOwnerId());
			petEntity.setOwner(owner);
		}
		return petEntity;
	}

	public static Owner mapOwnerEntityToOwner(OwnerEntity ownerEntity) {
		Owner owner = new Owner();
		owner.setId(ownerEntity.getOwnerId());
		owner.setCity(ownerEntity.getCity());
		owner.setFirstName(ownerEntity.getFirstName());
		owner.setLastName(ownerEntity.getLastName());
		List<Long> pets = new ArrayList<Long>();
		ownerEntity.getPets().forEach((pet) -> pets.add(pet.getPetId()));
		owner.setPetId(pets);
		return owner;
	}

}
