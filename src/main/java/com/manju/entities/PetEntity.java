package com.manju.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="pet")
public class PetEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petId;
	private String name;
	private Date birthDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")	
	@JsonBackReference
	private OwnerEntity owner;
	
	public OwnerEntity getOwner() {
		return owner;
	}
	public void setOwner(OwnerEntity owner) {
		this.owner = owner;
	}
	public Long getPetId() {
		return petId;
	}
	public void setPetId(Long petId) {
		this.petId = petId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
	
	

}
