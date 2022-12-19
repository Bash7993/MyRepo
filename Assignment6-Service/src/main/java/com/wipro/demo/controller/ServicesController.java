package com.wipro.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.demo.model.Services;
import com.wipro.demo.service.ServicesClass;

@Controller
public class ServicesController {
	@Autowired
	ServicesClass sc;

	@GetMapping("/")
	public String index() {
		return "addService"; // logical name "addService" gets mapped to physical view ->
								// /templates/addService.html
	}

	@PostMapping("/addService")
	public String addService(@ModelAttribute("patientId") Long patientId,
			@ModelAttribute("serviceName") String serviceName, @ModelAttribute("fees") Float fees) {
		System.out.println("Inside the controller addService post method");

		Services service = new Services(patientId, serviceName, fees);

		Services savedService = sc.createService(service);
		if (savedService != null) {
			return "successService";
		} else {
			return "fail";
		}
	}

	@GetMapping("/services/{patientId}")
	@ResponseBody
	public List<Services> getServices(@PathVariable Long patientId) {
		return sc.getAllServices(patientId);
	}
	@GetMapping("/getAllServices")
	@ResponseBody
	public ModelAndView getAllServices() {
		return sc.getServices();
	}

	@GetMapping("/count")
	@ResponseBody
	public String getServiceCount() {
		return String.valueOf(sc.getServices().getModel().size());
	}

	@GetMapping("/service/{serviceId}")
	@ResponseBody
	public Services getService(@PathVariable Long serviceId) {
		return sc.getServiceById(serviceId);
	}
	

}
