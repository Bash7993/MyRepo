package com.wipro.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Assignment6Service1Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment6Service1Application.class, args);
	}

}
