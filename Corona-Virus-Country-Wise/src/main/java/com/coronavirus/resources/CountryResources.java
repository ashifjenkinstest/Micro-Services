package com.coronavirus.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coronavirus.datatypes.CVACountry;
import com.coronavirus.models.CVACountries;
import com.coronavirus.services.CountriesService;

@RestController
public class CountryResources {
	
	@Autowired
	private CountriesService countriesService;
	
	
	@RequestMapping(value = "/countries",method = RequestMethod.GET)
	public List<CVACountry> getAllCountries(){
		
		//return Arrays.asList(new CVACountries("Finland", "finland", "FOI"), new CVACountries("Malta", "malta", "MT"));
		return countriesService.getAllCountriesFromExtAPI();	
		
	}

}
