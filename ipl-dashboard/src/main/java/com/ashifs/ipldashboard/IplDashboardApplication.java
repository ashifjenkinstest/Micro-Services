package com.ashifs.ipldashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EntityScan(basePackages = { "com.ashifs.*" })
@ComponentScan(basePackages = { "com.ashifs.*" })
@EnableJpaRepositories(basePackages = "com.ashifs.repositories")
public class IplDashboardApplication {

	public static void main(String[] args) {

		SpringApplication.run(IplDashboardApplication.class, args);
		System.out.println("<===========================================>");
		System.out.println("<\t Ipl  Dashboard  Application\t  >");
		System.out.println("<===========================================>");
	}

}
