package com.wipro.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

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
//	public Patient updatePatient(Patient p, Long id) {
//		Patient p1=new Patient();
//		if(p1==null) {
//			p1.setFirstname(p.getFirstname());
//			p1.setLastname(p.getLastname());
//		}else {
//			return "No patient record found by this id"+id;
//		}
//		
//		return pr.save(p1);
//	}
     public ModelAndView getAll(Long patientId) {
    	 ModelAndView mv=new ModelAndView();
    	 mv.addObject("patient",this.getPatientByID(patientId));
    	 final String uri="http://localhost:8095/services/"+""+patientId;
    	 
    	 @SuppressWarnings("unchecked")
		List<Services> result = rt.getForObject(uri, List.class);
 	    System.out.println("details of results:"+result.toString());
 	    
 	    mv.addObject("services",result);
 	    
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
