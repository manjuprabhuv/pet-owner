package com.manju.repositories;

import org.springframework.stereotype.Repository;


import com.manju.entities.PetEntity;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface  PetRepository extends CrudRepository<PetEntity,Long>{
	

}
