package com.ashifs.cloudgateway_1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackMethodController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod() {
        return "USER-SERVICE-EUREKACLIENT is taking longer than exected , please try again after some time...! ";

    }

    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallBackMethod() {
        return "DEPARTMENT-SERVICE-EUREKACLIENT is taking longer than exected , please try again after some time...! ";

    }

}
