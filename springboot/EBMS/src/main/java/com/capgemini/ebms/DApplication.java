package com.capgemini.ebms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DApplication {

	public static void main(String[] args) {
		SpringApplication.run(DApplication.class, args);
		System.out.println("Hi everyone");
	}

}
