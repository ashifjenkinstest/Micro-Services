package com.microservices;

import java.io.IOException;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservices.utils.LoadDataFromUri;

@SpringBootApplication
@Configuration 
public class CovidBatchServiceApplication {

	
	public static void main(String[] args) throws IOException {
		//SpringApplication.run(CovidBatchServiceApplication.class, args);
		SpringApplication.exit(SpringApplication.run(CovidBatchServiceApplication.class, args));
	}
	
	
	

}
