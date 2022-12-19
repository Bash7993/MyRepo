package com.wipro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.model.Patient;
import com.wipro.repository.PatientRepository;

@Service
public class PatientService {
	@Autowired
	PatientRepository pr;

	public Patient addPatient(Patient patient) {

		return pr.save(patient);
	}

	public ModelAndView getPatients() {
		ModelAndView mv = new ModelAndView();
		List<Patient> patients = pr.findAll();
		mv.addObject("patientDtl", patients);
		mv.setViewName("DisplayPatientDetail");
		return mv;
	}

	public Optional<Patient> getPatient(Long patientId) {	
		return pr.findById(patientId);
	}

}
