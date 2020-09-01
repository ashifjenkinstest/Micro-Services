package com.coronavirus.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coronavirus.dummy.Ponto;
import com.coronavirus.models.CoronaCountryDataStat;
import com.coronavirus.models.CoronaLocationStat;
import com.coronavirus.services.CoronaVirusDataServiceImpl;

@Controller
public class CoronaControllerV1 {
	
	
	@Autowired
	CoronaVirusDataServiceImpl coronaVirusDataServiceImpl;

	@RequestMapping("/")
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
			model.addAttribute("CoronaLocationStats", coronaVirusDataServiceImpl.getCoronaLocationStats());
			int totalCasesToday = 0;
			int totalCases = 0;
			int totalCasesTodayIndia = 0;
			int totalCasesIndia = 0;
			for (CoronaLocationStat stat : coronaVirusDataServiceImpl.getCoronaLocationStats()) {
				totalCasesToday += stat.getLatestCases();
				totalCases += stat.getTotalCases();
				if (stat.getCountry().equalsIgnoreCase("INDIA")) {
					totalCasesTodayIndia += stat.getLatestCases();
					totalCasesIndia += stat.getTotalCases();
				}
			}
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

}
