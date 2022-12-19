package com.wipro.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.demo.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
