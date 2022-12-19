package com.wipro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.model.Services;
import com.wipro.repository.ServicesRepository;

@Service
public class ServicesService {

	@Autowired
	ServicesRepository sRepo;

	public Services addService(Services service) {

		return sRepo.save(service);
	}

	public ModelAndView getServices() {
		ModelAndView mv = new ModelAndView();
		List<Services> services= sRepo.findAll();
		mv.addObject("serviceDtl", services);
		mv.setViewName("DisplayServiceDetail");
		return mv;
	}

	public Optional<Services> getService(Long serviceId) {
		return sRepo.findById(serviceId);
	}

}
