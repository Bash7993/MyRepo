package com.wipro.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.demo.model.Services;

import io.vavr.collection.Stream;

public interface ServicesRepository extends JpaRepository<Services, Long> {

	List<Services> findAllByPatientId(Long patientId);
}