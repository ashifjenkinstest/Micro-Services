package com.ashifs.hystrixdashbooard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableEurekaClient
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashbooardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashbooardApplication.class, args);
	}

}
