package com.wipro.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.model.Services;
import com.wipro.service.ServicesService;

@Controller
public class ServiceController {

	@Autowired
	ServicesService ss;

	@PostMapping("/addService")
	public String addService(@ModelAttribute("patientId") Long patientId,@ModelAttribute("serviceName") String serviceName, @ModelAttribute("fees") Float fees) {
		System.out.println("Inside the controller addService post method");

		Services service = new Services(patientId,serviceName, fees);

		Services savedService = ss.addService(service);
		if (savedService != null) {
			return "successService"; 
		} else {
			return "fail"; 
		}
	}

	@GetMapping("/getAllServices")
	public ModelAndView getServices() {
		return ss.getServices();
	}

	@GetMapping("/service/{serviceId}")
	@ResponseBody
	public Optional<Services> getCafe(@PathVariable("serviceId") Long serviceId) {
		return ss.getService(serviceId);
	}

	@GetMapping("/service/count")
	@ResponseBody
	public String getServiceCount() {
		return String.valueOf(ss.getServices().getModelMap().size());
	}
	
	@GetMapping("/addService")
	public String addService() {
		return "addService"; 
		}
}
