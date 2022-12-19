package com.wipro.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Patient {

	@Id
	@GeneratedValue
	@Column(name=" Patient_ID")
	private Long patientId;
	@Column(name=" FirstName")
	private String firstname;
	@Column(name=" LastName")
	private String lastname;
	public Patient(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	
}
