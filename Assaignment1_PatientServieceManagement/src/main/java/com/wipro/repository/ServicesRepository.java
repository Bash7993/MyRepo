package com.wipro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.model.Services;

public interface ServicesRepository extends JpaRepository<Services, Long> {

	public Optional<Services> findById(Long serviceId);
}
