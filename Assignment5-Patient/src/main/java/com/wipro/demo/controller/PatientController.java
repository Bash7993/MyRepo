package com.wipro.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.demo.model.Patient;
import com.wipro.demo.model.Services;
import com.wipro.demo.service.PatientService;

@Controller
@EnableAutoConfiguration
public class PatientController {
	@Autowired
	PatientService ps;

	@GetMapping("/")
	public String index() {
		return "index"; // logical name "index" gets mapped to physical view -> /templates/index.html
	}

	/*
	 * @PostMapping("/patient") public String
	 * addPatient(@ModelAttribute("firstName") String firstName,
	 * 
	 * @ModelAttribute("lastName") String lastName) {
	 * System.out.println("Inside the controller addPatient post method"); Patient
	 * patient = new Patient(firstName, lastName);
	 * 
	 * // // How to get Services // List<Services> l = new ArrayList<>(); // Long
	 * patientId = null; // Services s = new Services(patientId,serviceName, fees);
	 * // l.add(s); // // Patient patient = new Patient(firstName, lastName, l);
	 * 
	 * Patient savedPatient = ps.addPatient(patient); // control goes to the service
	 * class-addPatient method if (savedPatient != null) { return "success"; //
	 * templates/success.html } else { return "fail"; // templates/fail.html } }
	 * 
	 */
	@PostMapping("/patient")
	public String addPatient(@ModelAttribute("firstName") String firstName, @ModelAttribute("lastName") String lastName,
			@ModelAttribute("serviceName") String serviceName, @ModelAttribute("fees") Float fees) {
		System.out.println("Inside the controller addPatient post method");

		// How to get Services
		List<Services> l = new ArrayList<>();
		Long patientId = null;
		Services s = new Services(patientId, serviceName, fees);
		l.add(s);

		Patient patient = new Patient(firstName, lastName, l);

		Patient savedPatient = ps.addPatient(patient); // control goes to the service class-addPatient method
		if (savedPatient != null) {
			return "success"; // templates/success.html
		} else {
			return "fail"; // templates/fail.html
		}
	}

	@GetMapping("/allpatients/{patientId}")
	public ModelAndView getPatients(@PathVariable Long patientId) {
		return ps.getAll(patientId);

	}

//
//	@GetMapping("/patients")
//	@ResponseBody	
//	public List<Patient> getAllPatients() {
//		return ps.getPatients();
//	}
	@GetMapping("/patient")
	public ModelAndView getPatients() {
		return ps.getPatient();

	}

	@GetMapping("/patient/{patientId}")
	@ResponseBody
	public Patient getPatient(@PathVariable("patientId") Long patientId) {
		return ps.getPatientByID(patientId);
	}

	@GetMapping("/count")
	@ResponseBody
	public String getPatientCount() {
		return String.valueOf(ps.getPatients().size());
	}
//
//	@GetMapping("/regPatient")
//	public String registerPatient() {
//		return "patient";
//	}
}
