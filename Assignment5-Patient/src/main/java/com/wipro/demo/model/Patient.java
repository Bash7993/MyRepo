package com.wipro.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
public class Patient {

	@Id
	@GeneratedValue
	@Column(name = " Patient_ID")
	private Long patientId;
	@Column(name = " FirstName")
	private String firstname;
	@Column(name = " lastName")
	private String lastname;

	@OneToMany(targetEntity = Services.class, cascade = CascadeType.ALL)
	private List<Services> services = new ArrayList<>();

	public Patient(String firstname, String lastname, List<Services> services) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	this.services = services;
	}

}
