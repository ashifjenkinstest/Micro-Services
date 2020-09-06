package com.coronavirus.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.coronavirus.dummy.Ponto;
import com.coronavirus.models.CoronaCountryDataStat;
import com.coronavirus.models.CoronaLocationStat;
import com.coronavirus.models.CountryData;
import com.coronavirus.services.CoronaVirusDataServiceImpl;
import com.coronavirus.services.CountryDataService;

@Controller
public class CoronaControllerV1 {
	
	
	@Autowired
	private CoronaVirusDataServiceImpl coronaVirusDataServiceImpl;
	@Autowired
	private CountryDataService countryDataService;
	
	@RequestMapping(value = "/")
	public String home(Model model) {
		coronaVirusDataServiceImpl.getDataFromGitURL();
		model.addAttribute("fsmessage", "Welcome to the Home page");
		return "err";
		
	}
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("fsmessage", "Welcome to the Admin page");
		return "err";
		
	}
	
	@RequestMapping("/future")
	public String future(Model model) {
		model.addAttribute("fsmessage", "Welcome to the Future , its Bright :) ");
		return "err";
		
	}

	
	@RequestMapping("/countriesV1")
	public String countriesV1(Model model) {
		System.out.println("Enter countriesV1");
		// System.out.println(coronaVirusDataService.getCoronaLocationStats().size());
		if (coronaVirusDataServiceImpl.getCoronaLocationStats().size() > 0) {
			int totalCasesToday = 0;
			int totalCases = 0;
			int totalCasesTodayIndia = 0;
			int totalCasesIndia = 0;
			int i = 0;
			List<CoronaLocationStat> coronaLocationStats = coronaVirusDataServiceImpl.getCoronaLocationStats();
			for (CoronaLocationStat stat : coronaLocationStats) {
				if(countryDataService.checkIfAdded(stat.getId())) {
					stat.setStatus("ADDED");
					
					coronaLocationStats.set(i, stat);
				}else {
					stat.setStatus("ADD");
					coronaLocationStats.set(i, stat);
				}	
				i++;
				
				totalCasesToday += stat.getLatestCases();
				totalCases += stat.getTotalCases();
				if (stat.getCountry().equalsIgnoreCase("INDIA")) {
					totalCasesTodayIndia += stat.getLatestCases();
					totalCasesIndia += stat.getTotalCases();
				}
			}
			
			
			model.addAttribute("CoronaLocationStats", coronaLocationStats);
			
			model.addAttribute("totalCasesToday", totalCasesToday);
			model.addAttribute("totalCases", totalCases);

			model.addAttribute("totalCasesTodayIndia", totalCasesTodayIndia);
			model.addAttribute("totalCasesIndia", totalCasesIndia);
			return "countries";

		}
		model.addAttribute("fsmessage", "Oops : Failed to load data of all countries!");
		return "err";

	}
	
	
	@RequestMapping("countryV1")
	public String getCountryStateRatesV1(@PathParam(value = "country") String country,
			@PathParam(value = "state") String state, Model model) {
		System.out.println("Enter getCountryStateRatesV1");

		CoronaCountryDataStat coronaCountryDataStat = null;

		coronaCountryDataStat = coronaVirusDataServiceImpl.getCountryStat(country, state);
		String countAndState = null;
		if (coronaCountryDataStat.getCountry() == null ) {
			model.addAttribute("fsmessage", "Oops : Failed to load data of " + country + "!");
			return "err";
			
		}
		countAndState = (coronaCountryDataStat.getState().toUpperCase().length() > 0  ? 
				coronaCountryDataStat.getCountry().toUpperCase().concat(", " + coronaCountryDataStat.getState().toUpperCase()) :
				coronaCountryDataStat.getCountry().toUpperCase());
		model.addAttribute("country", countAndState);
		model.addAttribute("totalCasesToday", coronaCountryDataStat.getLatestCases());
		model.addAttribute("totalCases", coronaCountryDataStat.getTotalCases());

		List<Ponto> pontos = new ArrayList<>();
		Ponto ponto = null;
		for (Map.Entry<Integer, Integer> entry : coronaCountryDataStat.getDateVsCount().entrySet()) {

			ponto = new Ponto();
			ponto.setX(entry.getKey());
			ponto.setY(entry.getValue());
			pontos.add(ponto);

		}
		model.addAttribute("pontos", pontos);
		return "country";

	}
	
	
	
	@RequestMapping(value = "/add-country", method = RequestMethod.GET)
	//@ResponseBody
	public String addCountry(@PathParam(value = "id")int id,
							 @PathParam(value = "country") String country,
							 @PathParam(value = "state") String state,
							 @PathParam(value = "totalCases") String totalCases,
							 @PathParam(value = "latestCases") String latestCases,
							 Model model) {
		String messageStr = null;
		
		System.out.println("add-country called");
		CountryData countryData = new CountryData( id, country, state, totalCases, latestCases);
		countryDataService.addCountry(countryData);
		List<CountryData> addedCountryDataList = new ArrayList<CountryData>();
		addedCountryDataList = countryDataService.showAllAddedCountries();
		model.addAttribute("addedCountryDataList",addedCountryDataList) ;
		if (state.length()>1) {
			 messageStr = country + ", " + state + "\nTotal Cases :" +
					 		totalCases + " and Latest Cases :" + latestCases + "\nAdded in the Database...";
		}else {
		 messageStr = country +  "\nTotal Cases :" + totalCases + " and Latest Cases :" + latestCases + "\nAdded in the Database...";
		}
		model.addAttribute("fsmessage", messageStr);
		return "loadedCountry";
		
	}
	
	
	@RequestMapping(value = "/delete-country", method = RequestMethod.GET)
	//@ResponseBody
	public String deleteCountry(@PathParam(value = "id")int id,
							 @PathParam(value = "country") String country,Model model) {
		
		countryDataService.deleteCountry(id, country);
		List<CountryData> addedCountryDataList = new ArrayList<CountryData>();
		addedCountryDataList = countryDataService.showAllAddedCountries();
		model.addAttribute("addedCountryDataList",addedCountryDataList) ;
		String messageStr = null;
		messageStr = country + "\nDeleted from the Database...";
		model.addAttribute("fsmessage", messageStr);
		return "loadedCountry";
		
	
		
	}
	
	
	@RequestMapping(value = "/update-country", method = RequestMethod.GET)
	//@ResponseBody
	public String updateCountry(@PathParam(value = "id")int id,
							 @PathParam(value = "country") String country,
							 @PathParam(value = "state") String state,
							 @PathParam(value = "totalCases") String totalCases,
							 @PathParam(value = "latestCases") String latestCases,
							 Model model) {
		String messageStr = null;
		
		System.out.println("udate-country called");
		CountryData countryData = new CountryData( id, country, state, totalCases, latestCases);
		countryDataService.addCountry(countryData);
		List<CountryData> updatedCountryDataList = new ArrayList<CountryData>();
		updatedCountryDataList = countryDataService.showAllAddedCountries();
		model.addAttribute("addedCountryDataList",updatedCountryDataList) ;
		if (state.length()>1) {
			 messageStr = country + ", " + state + "\nTotal Cases :" +
					 		totalCases + " and Latest Cases :" + latestCases + "\nUpdated in the Database...";
		}else {
		 messageStr = country +  "\nTotal Cases :" + totalCases + " and Latest Cases :" + latestCases + "\nUpdated in the Database...";
		}
		model.addAttribute("fsmessage", messageStr);
		return "loadedCountry";
		
	}
	
	
	@RequestMapping(value = "/loaded-countries", method = RequestMethod.GET)
	//@ResponseBody
	public String loadedCountries(Model model) {
		
		List<CountryData> addedCountryDataList = new ArrayList<CountryData>();
		addedCountryDataList = countryDataService.showAllAddedCountries();
		model.addAttribute("addedCountryDataList",addedCountryDataList) ;
		String messageStr = null;
		messageStr = "\nCountries from the Database...";
		model.addAttribute("fsmessage", messageStr);
		return "updateLoadedCountry";
		
	
		
	}
	

}
