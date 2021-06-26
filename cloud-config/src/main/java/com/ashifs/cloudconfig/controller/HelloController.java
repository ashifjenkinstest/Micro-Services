package com.ashifs.cloudconfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @Value("${spring.application.name}")
    static String application;

    @GetMapping("get")
    public String testController() {
        return "Hello from " + application;
    }

}
