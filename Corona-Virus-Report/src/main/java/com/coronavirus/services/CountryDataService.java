package com.coronavirus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coronavirus.models.CountryData;
import com.coronavirus.repositories.CountryDataRepository;

@Service
public class CountryDataService { 

	@Autowired
	private CountryDataRepository countryDataRepository;
	
	public void addCountry(CountryData countryData) {
		if(countryData != null)
		System.out.println(countryDataRepository.save(countryData));
		
	}
	public List<CountryData> showAllAddedCountries() {
		List<CountryData> countryDataList = new ArrayList<CountryData>();
		countryDataList = (List<CountryData>) countryDataRepository.findAll();
		/*
		for(CountryData countryData : countryDataList) {
			System.out.println(countryData.toString());
		}
		*/
		return countryDataList;
	}
	
	
	
	public void deleteCountry(Integer id,String country) {
		System.out.println("Enter addCountry");
		if(id != 0 && country !=null) {
			if(countryDataRepository.existsById(id)) {
				System.out.println(countryDataRepository.findById(id).toString());
				countryDataRepository.deleteById(id);
				
			}
		}
		
	}
	
	public Boolean checkIfAdded(Integer id) {
		System.out.println("checkIfAdded " +countryDataRepository.existsById(id) );
		
		return countryDataRepository.existsById(id);
		
	}
	
	
}
