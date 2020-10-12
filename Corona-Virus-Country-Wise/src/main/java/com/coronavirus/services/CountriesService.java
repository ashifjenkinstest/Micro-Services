package com.coronavirus.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.coronavirus.datatypes.CVACountry;
import com.coronavirus.models.CVACountries;
import com.coronavirus.repositories.CVACountriesReposirtory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class CountriesService {
	

	@Value("${api.url}")
	private String apiUrl;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private CVACountriesReposirtory cvaCountriesReposirtory;
	
	
	
	@PostConstruct
	@Scheduled(cron = "0 0 0 * * *")
	public void countriesServiceInit() {
		System.out.println("Enter countriesServiceInit");
		System.out.println(getAllCountriesFromExtAPI().getClass());	
		System.out.println("Exit countriesServiceInit");
	}
	
	public List<CVACountry> getAllCountriesFromExtAPI() {
		
				return null;
		
	}
	
	
	public void storeAllCountries(List<CVACountries> cvaCountries) {
		System.out.println("Enter storeAllCountries");
		if(cvaCountries !=null && cvaCountries.size()>0) {
			System.out.println(cvaCountries.size());
			cvaCountriesReposirtory.saveAll(cvaCountries);
		}
		System.out.println("Exit storeAllCountries");
		
		
	}
	
	public void storeCountry(CVACountries cvaCountry) {
		System.out.println("Enter storeAllCountries");
		if(cvaCountry !=null ) {
			cvaCountriesReposirtory.save(cvaCountry);
		}
		System.out.println("Exit storeAllCountries");
		
		
	}
	
	
	public List<CVACountries> retrieveAllCountries() {
		return cvaCountriesReposirtory.findAll(/*Sort.by(Sort.Direction.ASC,"Country")*/);
		
	}
	
	
	
	
	
		

	
	
}
