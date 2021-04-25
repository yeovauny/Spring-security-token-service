package com.tyr.autentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



// I need to search more, becouse for this moments this project only working for me
// with this configuration package
@SpringBootApplication (scanBasePackages = {"com.tyr.controller",
		"com.tyr.persistence",
		"com.tyr.autentication.service.token"})
public class ServiceTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceTokenApplication.class, args);
	}



}
