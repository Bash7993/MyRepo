package com.wipro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.model.Patient;
import com.wipro.model.Services;
import com.wipro.service.PatientService;

@Controller
public class PatientController {
	@Autowired
	PatientService ps;

	@GetMapping("/")
	public String index() {
		return "index"; // logical name "index" gets mapped to physical view -> /templates/index.html
	}

	@PostMapping("/patient")
	public String addPatient(@ModelAttribute("firstName") String firstName,
			@ModelAttribute("lastName") String lastName, @ModelAttribute("serviceName") String serviceName,
			@ModelAttribute("fees") Float fees) {
		System.out.println("Inside the controller addPatient post method");

		// How to get Services
		List<Services> l = new ArrayList<>();
		Long patientId = null;
		Services s = new Services(patientId,serviceName, fees);
		l.add(s);

		Patient patient = new Patient(firstName, lastName, l);

		Patient savedPatient = ps.addPatient(patient); // control goes to the service class-addPatient method
		if (savedPatient != null) {
			return "successPatient"; // templates/success.html
		} else {
			return "fail"; // templates/fail.html
		}
	}

	@GetMapping("/patient")
	public ModelAndView getPatients() {
		return ps.getPatients();
		
	}

	@GetMapping("/patient/{patientId}")
	@ResponseBody
	public Optional<Patient> getPatient(@PathVariable("patientId") Long patientId) {
		return ps.getPatient(patientId);
	}

	@GetMapping("/patient/count")
	@ResponseBody
	public String getPatientCount() {
		return String.valueOf(ps.getPatients().getModel().size());
	}
	
	@GetMapping("/regPatient")
	public String registerPatient() {
		return "patient"; 
		}

}
