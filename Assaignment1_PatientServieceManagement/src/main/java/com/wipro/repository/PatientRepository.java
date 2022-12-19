package com.wipro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	public Optional<Patient> findById(Long patientId);
	
	public Patient save(Long patientId);
}
