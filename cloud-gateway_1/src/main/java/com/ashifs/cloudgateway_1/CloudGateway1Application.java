package com.ashifs.cloudgateway_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
@EnableCircuitBreaker
@RefreshScope
public class CloudGateway1Application {

	public static void main(String[] args) {
		SpringApplication.run(CloudGateway1Application.class, args);
	}

}
