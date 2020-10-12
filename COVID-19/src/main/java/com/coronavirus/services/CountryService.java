package com.coronavirus.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.coronavirus.models.Country;
import com.coronavirus.repositories.CountryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class CountryService {
	
	@Value("${api.url}")
	private String apiUrl;
	

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private CountryRepository countryRepositories;

	//@PostConstruct
	@Scheduled(cron = "0 0 0 * * *")
	@HystrixCommand(fallbackMethod = "loadAddCountriesFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
			/*Wait for 2 second if no response then timeout.*/,
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6")
			/*The number of requests it will send.*/,
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
			/*Percentage of last failed requests, If 3 out of last 6 requests sent fails then fallback will be called.*/,
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			/*Wait for 5 seconds before sending the  next set of requests.*/,
    })
	public ArrayList<Country> loadAddCountries() {
		
		ObjectMapper mapper =new ObjectMapper();
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode countries = webClientBuilder.build().get().uri(apiUrl  + "/countries").retrieve().bodyToMono(JsonNode.class).block();
		System.out.println(webClientBuilder.build().get().uri(apiUrl  + "/countries").retrieve().bodyToMono(JsonNode.class).block());
		
		//Jackson's use of generics here are completely unsafe, but that's another issue
		ArrayList<Country> countryList = mapper.convertValue(
				countries, 
		    new TypeReference<ArrayList<Country>>(){}
		);

		/*
		for (Country country : countryList) {
			System.out.println(country);
		}
		*/
		
		countryRepositories.saveAll(countryList);
		return  countryList;
	}
	
	public ArrayList<Country> loadAddCountriesFallback() {
		return (ArrayList<Country>) Arrays.asList(new Country("Not found","",""));
	}
	
	@HystrixCommand(fallbackMethod = "getAllCountriesFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
			/*Wait for 2 second if no response then timeout.*/,
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6")
			/*The number of requests it will send.*/,
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
			/*Percentage of last failed requests, If 3 out of last 6 requests sent fails then fallback will be called.*/,
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			/*Wait for 5 seconds before sending the  next set of requests.*/,
    })
	public List<Country> getAllCountries(){
		
		return  (List<Country>) countryRepositories.findAllByOrderByCountryAsc();
		
	}
	
	
	public ArrayList<Country> getAllCountriesFallback(){
		return (ArrayList<Country>) Arrays.asList(new Country("Not found","",""));
		
	}
}
