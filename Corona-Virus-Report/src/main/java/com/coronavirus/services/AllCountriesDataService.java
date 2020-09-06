package com.coronavirus.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coronavirus.models.AllCountriesData;
import com.coronavirus.models.CoronaLocationStat;
import com.coronavirus.repositories.AllCountriesDataRepository;

@Service
public class AllCountriesDataService {
	
	@Autowired
	private AllCountriesDataRepository allCountriesDataRepository;
	
	
	public void insertAllCountriesData(List<CoronaLocationStat> coronaLocationStats) {
		AllCountriesData allCountriesData = new AllCountriesData();
		for (CoronaLocationStat coronaLocationStat : coronaLocationStats) {
			allCountriesData.setId(coronaLocationStat.getId());
			allCountriesData.setCountry(coronaLocationStat.getCountry());
			allCountriesData.setState(coronaLocationStat.getState());
			allCountriesData.setTotalCases(coronaLocationStat.getTotalCases());
			allCountriesData.setLatestCases(coronaLocationStat.getLatestCases());
			
			allCountriesDataRepository.save(allCountriesData);
			
			
			
		}
		
		
		
	}
	
	public List<AllCountriesData> showAllCountriesData() {
		
		return allCountriesDataRepository.findAll();
		
	}

}
