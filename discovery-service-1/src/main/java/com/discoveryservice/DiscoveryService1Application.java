package com.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryService1Application {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryService1Application.class, args);
	}

}
