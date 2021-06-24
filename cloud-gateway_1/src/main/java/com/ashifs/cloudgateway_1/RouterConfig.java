package com.ashifs.cloudgateway_1;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

        @Bean
        public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
                return builder.routes().route(r -> r.path("/departments/**")
                                .filters(f -> f.hystrix(h -> h.setName("DEPARTMENT-SERVICE")
                                                .setFallbackUri("forward:/departmentServiceFallBack")))
                                .uri("lb://DEPARTMENT-SERVICE-EUREKACLIENT").id("DEPARTMENT-SERVICE-EUREKACLIENT"))
                                .route(r -> r.path("/users/**")
                                                .filters(f -> f.hystrix(h -> h.setName("USER-SERVICE")
                                                                .setFallbackUri("forward:/userServiceFallBack")))
                                                .uri("lb://USER-SERVICE-EUREKACLIENT").id("USER-SERVICE-EUREKACLIENT"))
                                .build();
        }

}
