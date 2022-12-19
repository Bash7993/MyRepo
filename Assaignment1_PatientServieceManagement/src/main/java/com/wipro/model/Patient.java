package com.wipro.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@NoArgsConstructor
@ToString
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientId;
	private String firstname;
	private String lastname;
	@OneToMany(targetEntity=Services.class, cascade = CascadeType.ALL)
	private List<Services> services = new ArrayList<>();
	

	public Patient(String firstname, String lastname, List<Services> services) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.services = services;
	}

	
}
