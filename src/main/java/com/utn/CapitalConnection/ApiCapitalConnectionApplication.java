package com.utn.CapitalConnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.utn.CapitalConnection")
public class ApiCapitalConnectionApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiCapitalConnectionApplication.class, args);
	}
}
