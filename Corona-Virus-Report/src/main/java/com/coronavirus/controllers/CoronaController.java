package com.coronavirus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coronavirus.models.CoronaLocationStat;
import com.coronavirus.services.CoronaVirusDataService;

@Controller
public class CoronaController {
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@RequestMapping("/")
	public String home(Model  model) {
		System.out.println("Enter home");
		//System.out.println(coronaVirusDataService.getCoronaLocationStats().size());
		if(coronaVirusDataService.getCoronaLocationStats().size()>0) {
			model.addAttribute("CoronaLocationStats" ,coronaVirusDataService.getCoronaLocationStats());
			int totalCasesToday = 0;
			int totalCases = 0;
			int totalCasesTodayIndia = 0;
			int totalCasesIndia = 0;
			for (CoronaLocationStat stat : coronaVirusDataService.getCoronaLocationStats()) {
				totalCasesToday += stat.getLatestCases();
				totalCases += stat.getTotalCases();
				if(stat.getCountry().equalsIgnoreCase("INDIA")) {
					totalCasesTodayIndia += stat.getLatestCases();
					totalCasesIndia +=stat.getTotalCases();
				}
				
			}
			//System.out.println(totalCasesTodayIndia);
			//System.out.println(totalCasesIndia);
			
			model.addAttribute("totalCasesToday" ,totalCasesToday);
			model.addAttribute("totalCases" ,totalCases);
			
			model.addAttribute("totalCasesTodayIndia" ,totalCasesTodayIndia);
			model.addAttribute("totalCasesIndia" ,totalCasesIndia);
			
			
			return "home";
		
			
		}
		return "err";
			
	}
	
	
	

}
