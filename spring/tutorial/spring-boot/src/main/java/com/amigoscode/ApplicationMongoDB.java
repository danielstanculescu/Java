package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// for MongoDB
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@RestController
public class ApplicationMongoDB {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMongoDB.class, args);
	}

	//@GetMapping
	public String helloWorld(){
		return "Hello World Spring Boot";
	}
}