package com.wipro.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.demo.model.Services;
import com.wipro.demo.repo.ServicesRepository;

@Service
public class ServicesClass {
	@Autowired
	ServicesRepository sr;

	public Services createService(Services service) {
		return sr.save(service);
	}

	public List<Services> getAllServices(Long patientId) {
		return sr.findAllByPatientId(patientId);
	}

	public Services getServiceById(Long serviceId) {
		return sr.findById(serviceId).get();
	}

	public ModelAndView getServices() {
		ModelAndView mv = new ModelAndView();
		List<Services> services = sr.findAll();
		mv.addObject("serviceDtl", services);
		mv.setViewName("DisplayServiceDetail");
		return mv;
	}

}
