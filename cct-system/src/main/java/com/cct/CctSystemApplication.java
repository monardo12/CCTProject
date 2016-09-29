package com.cct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cct")
public class CctSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CctSystemApplication.class, args);
	}

}
