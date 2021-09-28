package com.tyr.example.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
//@EnableResourceServer
public class LsExampleResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LsExampleResourceApplication.class, args);
	}

}
