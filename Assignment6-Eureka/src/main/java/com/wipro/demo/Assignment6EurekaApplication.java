package com.wipro.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Assignment6EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assignment6EurekaApplication.class, args);
	}

}
