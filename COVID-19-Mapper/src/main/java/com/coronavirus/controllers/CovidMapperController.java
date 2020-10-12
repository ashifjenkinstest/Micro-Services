package com.coronavirus.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidMapperController {

	@ResponseBody
	@RequestMapping(value = "/")
	public String home() {
		return "COVID-19-Mapper";
	}
	
}
