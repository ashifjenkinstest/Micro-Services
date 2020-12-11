package com.microservices;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration 
public class CovidBatchServiceApplication {

	
	public static void main(String[] args) throws IOException {
		//SpringApplication.run(CovidBatchServiceApplication.class, args);
		SpringApplication.exit(SpringApplication.run(CovidBatchServiceApplication.class, args));
	}
	
	
	

}
