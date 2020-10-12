package com.coronavirus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Covid19EurekaSrvRegDiscApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19EurekaSrvRegDiscApplication.class, args);
	}

}
