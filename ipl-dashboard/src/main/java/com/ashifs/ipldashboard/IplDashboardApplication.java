package com.ashifs.ipldashboard;

import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Documentation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EntityScan(basePackages = { "com.ashifs.*" })
@ComponentScan(basePackages = { "com.ashifs.*" })
@EnableJpaRepositories(basePackages = "com.ashifs.*")
public class IplDashboardApplication {

	public static void main(String[] args) {

		SpringApplication.run(IplDashboardApplication.class, args);
		System.out.println("<===========================================>");
		System.out.println("<\t Ipl  Dashboard  Application..\t  >");
		System.out.println("<===========================================>");
	}

	@Bean
	public Docket swaggerConfiguration() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com."))
				.build().apiInfo(iplApiDetails());
	}

	private ApiInfo iplApiDetails() {
		return new ApiInfo("IPL DASHBOARD API", "Simple APIs for IPL teams,matches,players", "1.0",
				"Let's learn together...", new springfox.documentation.service.Contact("Ashif", "", ""), "API license",
				"http://nourl.com", Collections.emptyList());
	}

}
