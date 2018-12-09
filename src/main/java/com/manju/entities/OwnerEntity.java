package com.manju.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "owner")
public class OwnerEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ownerId;
	private String firstName;
	private String lastName;
	private String city;
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Set<PetEntity> pets = new HashSet<>();

	public Set<PetEntity> getPets() {
		return pets;
	}

	public void setPets(Set<PetEntity> pets) {
		this.pets = pets;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	
	

}
