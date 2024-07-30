package com.est.cranejob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// @EnableScheduling
@SpringBootApplication
public class CraneJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraneJobApplication.class, args);
	}

}
