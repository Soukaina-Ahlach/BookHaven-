package com.example.onlinebookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@EntityScan(basePackages = "org.bookhaven")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class OnlineBookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBookstoreApplication.class);
	}

}


