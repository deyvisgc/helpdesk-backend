package com.helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.boot.autoconfigure.SpringBootApplication
@ComponentScan(basePackages = "com.helpdesk")
public class SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}

}
