package com.wipro.demo.service;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.demo.model.Services;

@FeignClient(value = "PatientsServices-Service")
@LoadBalancerClient(name = "PatientsServices-Service", configuration = CloudProviderConfiguration.class)
public interface PatientProxy {
	
	@GetMapping("/services/{patientId}")
	List<Services> getService(@PathVariable Long patientId);

}
