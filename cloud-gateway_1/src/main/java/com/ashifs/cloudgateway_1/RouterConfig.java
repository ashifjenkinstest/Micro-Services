package com.ashifs.cloudgateway_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

        private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RouterConfig.class);

        @Bean
        public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
                logger.debug("Enter gatewayRoutes");
                logger.debug("Exit gatewayRoutes");
                return builder.routes()
                                .route(r -> r.path("/departments/**")
                                                .filters(f -> f.hystrix(h -> h.setName("DEPARTMENT-SERVICE-NAME")
                                                                .setFallbackUri("forward:/departmentServiceFallBack")))
                                                .uri("lb://DEPARTMENT-SERVICE").id("DEPARTMENT-SERVICE-ID"))
                                .route(r -> r.path("/users/**")
                                                .filters(f -> f.hystrix(h -> h.setName("USER-SERVICE-NAME")
                                                                .setFallbackUri("forward:/userServiceFallBack")))
                                                .uri("lb://USER-SERVICE").id("USER-SERVICE-ID"))
                                .build();
        }

}
