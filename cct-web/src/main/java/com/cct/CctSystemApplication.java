package com.cct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CctSystemApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(CctSystemApplication.class, args);
	}

}
