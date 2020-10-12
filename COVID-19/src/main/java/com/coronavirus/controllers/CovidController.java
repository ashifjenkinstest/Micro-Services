package com.coronavirus.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coronavirus.models.Country;
import com.coronavirus.models.CountryCaseHistory;
import com.coronavirus.services.CountryCaseHistoryService;
import com.coronavirus.services.CountryService;

@Controller
public class CovidController {
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CountryCaseHistoryService countryCaseHistoryService;

	@RequestMapping(method = RequestMethod.GET,value = "/getAllCountries")
	public String getAllCountries(Model model){
		List<Country> allCountries = countryService.getAllCountries();
		model.addAttribute("AllCountries", allCountries);
		return "all_countries";
	}
	
	//@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value = "/getCountryCaseHistory/{iso2}")
	public String  getCountryCaseHistory(@PathVariable(value = "iso2") String iso2,Model model){
		System.out.println("getCountryCaseHistory->iso2 " + iso2);
		String country = countryCaseHistoryService.loadCountriesCaseHistory(iso2);
		if(country != null){
			model.addAttribute("countryCaseHistory", countryCaseHistoryService.getCountryCaseHistory(country)) ;
		}else {
			model.addAttribute("countryCaseHistory", countryCaseHistoryService.getCountryCaseHistoryFallback(iso2));
		}
		
		return "show_country_cases_hist";
	}
}
