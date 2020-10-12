package com.coronavirus.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.coronavirus.models.Country;
import com.coronavirus.models.CountryCaseHistory;
import com.coronavirus.repositories.CountryCaseHistoryRepository;
import com.coronavirus.repositories.CountryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class CountryCaseHistoryService {
	
	@Value("${api.url}")
	private String apiUrl;
	

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private CountryCaseHistoryRepository countryCaseHistoryRepository;

	@Autowired
	private CountryRepository countryRepositories;
	
	//@PostConstruct
	//@Scheduled(cron = "0 0 0 * * *")
	@HystrixCommand(fallbackMethod = "loadCountriesCaseHistoryFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
			/*Wait for 2 second if no response then timeout.*/,
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6")
			/*The number of requests it will send.*/,
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
			/*Percentage of last failed requests, If 3 out of last 6 requests sent fails then fallback will be called.*/,
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			/*Wait for 5 seconds before sending the  next set of requests.*/,
    })
	public /*ArrayList<CountryCaseHistory>*/ String loadCountriesCaseHistory(String countryIso) {
		System.out.println("loadCountriesCaseHistory->countryIso " + countryIso);
		ObjectMapper mapper =new ObjectMapper();
		
		if(countryRepositories.findByIso2(countryIso) != null) {
			//System.out.println(countryRepositories.findByIso2(countryIso).toString());
			JsonNode countriesCaseHistory = webClientBuilder.build().get().uri(apiUrl  + "/dayone/country/"+ countryIso).retrieve().bodyToMono(JsonNode.class).block();
			
			
			//Jackson's use of generics here are completely unsafe, but that's another issue
			ArrayList<CountryCaseHistory> countryCaseHistories = mapper.convertValue(
				countriesCaseHistory,
				new TypeReference<ArrayList<CountryCaseHistory>>(){}
					);
			countryCaseHistoryRepository.saveAll(countryCaseHistories);

			return countryRepositories.findByIso2(countryIso).getCountry();
		}
			
		return null;
		
	}
	
	public ArrayList<Country> loadCountriesCaseHistoryFallback() {
		return (ArrayList<Country>) Arrays.asList(new Country("Not found","",""));
	}
	
	
	@HystrixCommand(fallbackMethod = "getCountryCaseHistoryFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
			/*Wait for 2 second if no response then timeout.*/,
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6")
			/*The number of requests it will send.*/,
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
			/*Percentage of last failed requests, If 3 out of last 6 requests sent fails then fallback will be called.*/,
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			/*Wait for 5 seconds before sending the  next set of requests.*/,
    })
	public List<CountryCaseHistory> getCountryCaseHistory(String country){
		return  (countryCaseHistoryRepository.findAllByCountryOrderByDateOfCaseDesc(country));
	}
	
	
	public List<CountryCaseHistory> getCountryCaseHistoryFallback(String country){
		return  Arrays.asList(new CountryCaseHistory(country, null, null, null, null, null, null, -1, -1, -1, null));
	}
	
		

}
