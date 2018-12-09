package com.manju.repositories;

import org.springframework.stereotype.Repository;

import com.manju.entities.OwnerEntity;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface  OwnerRepository extends CrudRepository<OwnerEntity,Long>{
	

}
