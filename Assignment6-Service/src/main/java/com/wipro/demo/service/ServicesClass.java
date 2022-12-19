package com.wipro.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.demo.model.Services;
import com.wipro.demo.repo.ServicesRepository;

@Service
public class ServicesClass {
	@Autowired
	ServicesRepository sr;

	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	public Services createService(Services service) {
		 return sr.save(service);
	}


//	@CircuitBreaker(name = "ServiceCircuitBreaker")
	public List<Services> getAllServices(Long patientId) {
		// return sr.findAllByPatientId(patientId);
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.zomato.com/v1/reviews.json/";
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("ServiceCircuitBreaker");
		return (List<Services>) circuitBreaker.run(() -> restTemplate.getForObject(url, List.class),
				throwable -> getDefaultServices());
	}

	public List<Services> getDefaultServices() {
		List<Services> list = new ArrayList<>();
		list.add(new Services(1l, "EOP", 500));
		list.add(new Services(2l, "OP", 100));
		list.add(new Services(3l, "X-Ray", 5000));
		list.add(new Services(4l, "CTScan", 10000));
		return list;
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
