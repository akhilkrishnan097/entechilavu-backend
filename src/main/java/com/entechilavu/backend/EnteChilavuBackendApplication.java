package com.entechilavu.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class EnteChilavuBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnteChilavuBackendApplication.class, args);
	}

}
