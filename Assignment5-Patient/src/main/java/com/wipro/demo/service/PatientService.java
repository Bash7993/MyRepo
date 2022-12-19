package com.wipro.demo.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import com.wipro.demo.model.Patient;
import com.wipro.demo.model.Services;
import com.wipro.demo.repo.PatientRepository;

@Service
public class PatientService {

	@Autowired
	PatientRepository pr;
	@Autowired
	RestTemplate rt;

	public Patient addPatient(Patient patient) {
		System.out.println(
				"new Patient details inside Patient portal:" + patient.getFirstname() + " " + patient.getLastname());
		System.out.println("repo object:" + pr);
		return pr.save(patient);
	}

	public List<Patient> getPatients() {
		return pr.findAll();
	}

	public Patient getPatientByID(Long patientId) {
		return pr.findById(patientId).get();
	}
	// use below 2 lines while using just eureka
	// @Autowired
	// private DiscoveryClient discoveryClient;
	
	//use below 2 lines while using Spring Cloud Load Balancer
		@Autowired
		private LoadBalancerClient loadBalancerClient;
	// use below 2 lines if using feign instead of restTemplate
	@Autowired
	private PatientProxy patientProxy;
	@Value("${pivotal.patientsservices.name}")
	protected String patientsservices;

	public ModelAndView getAll(Long patientId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("patient", this.getPatientByID(patientId));

		// below set of code might be required only if using RestTemplate
		// but while using FeignClient, only using the proxy line is enough

//		List<ServiceInstance> instances = discoveryClient.getInstances(patientsservices);
//		URI uri = instances.get(0).getUri();
//
//		System.out.println("Patients-Services.URI=========" + uri);
//		String url = uri.toString() + "/services/" + patientId;
//		System.out.println("========================================");
//		System.out.println("Patients-Services.URI=========" + url);
//		// final String uri = "http://localhost:8095/services/" + "" + patientId;
//
//		@SuppressWarnings("unchecked")
//		List<Services> result = rt.getForObject(url, List.class);
//		System.out.println("details of results:" + result.toString());
		
		//use 2 lines below if using Spring Cloud LoadBalancer
				ServiceInstance instance=loadBalancerClient.choose(patientsservices);
				//URI uri=instance.getUri();
				URI uri=URI.create(String.format("http://%s:%s",
						instance.getHost(),instance.getPort()));
				
				System.out.println("Patients-Services.URI=========" + uri);
				String url = uri.toString() + "/services/" + patientId;
				System.out.println("========================================");
				System.out.println("Patients-Services.URI=========" + url);
		/**
			Use Below 2 lines if using feign
		 */
		List<Services> result = patientProxy.getService(patientId);
		System.out.println("details of results:" + result.toString());
		mv.addObject("services", result);

		mv.setViewName("Patients");
		return mv;
	}

	public ModelAndView getPatient() {
		ModelAndView mv = new ModelAndView();
		List<Patient> patients = pr.findAll();
		mv.addObject("patientDtl", patients);
		mv.setViewName("DisplayPatientDetail");
		return mv;
	}
}
