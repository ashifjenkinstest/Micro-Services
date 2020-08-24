package com.coronavirus.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coronavirus.dummy.Ponto;
import com.coronavirus.garbage.CanvasjsChartService;
import com.coronavirus.models.CoronaCountryDataStat;
import com.coronavirus.models.CoronaLocationStat;
import com.coronavirus.models.DateVsCount;
import com.coronavirus.services.CoronaVirusDataService;

@Controller
//@RequestMapping("/TopicController")
public class CoronaController {

	@Autowired
	CoronaVirusDataService coronaVirusDataService;

	@Autowired
	private CanvasjsChartService canvasjsChartService;

	@RequestMapping("/")
	public String home(Model model) {
		System.out.println("Enter home");
		// System.out.println(coronaVirusDataService.getCoronaLocationStats().size());
		if (coronaVirusDataService.getCoronaLocationStats().size() > 0) {
			model.addAttribute("CoronaLocationStats", coronaVirusDataService.getCoronaLocationStats());
			int totalCasesToday = 0;
			int totalCases = 0;
			int totalCasesTodayIndia = 0;
			int totalCasesIndia = 0;
			for (CoronaLocationStat stat : coronaVirusDataService.getCoronaLocationStats()) {
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

			return "home";

		}
		return "err";

	}

	@RequestMapping("country")
	public String getCountryStateRates(@PathParam(value = "country") String country,
			@PathParam(value = "state") String state, Model model) {
		System.out.println("Enter getCountryStateRates");

		CoronaCountryDataStat coronaCountryDataStat = null;
		

		coronaCountryDataStat = coronaVirusDataService.getCountryStat(country, state);
		String countAndState = null;
		
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
