package com.coronavirus.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.coronavirus.models.CoronaLocationStat;
@Service
public class CoronaVirusDataService {

	private static final String CORONA_VIRUS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<CoronaLocationStat> coronaLocationStats  ;
	Iterable<CSVRecord> records;

	@PostConstruct
	@Scheduled(cron = "1 * * * * *")
	public void getVirusDataV1() {
		setCoronaLocationStats(getAllCountriesData(converStringToCSVV1(getDataFromGit())));
	}
	
	public String getDataFromGit() {
		System.out.println("Enter getDataFromGit");
		List<CoronaLocationStat> coronaLocationStats = new ArrayList<CoronaLocationStat>();
		int status = 0;
		URL url = null;
		try {
			 url = new URL(CORONA_VIRUS_URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection urlConnection =null;
		BufferedReader in = null;
		StringBuffer content = new StringBuffer();
		String inputLine;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(5000);
			urlConnection.setReadTimeout(3000);
			urlConnection.setRequestMethod("GET");
			try {status = urlConnection.getResponseCode();} catch (Exception e) 
			{
				urlConnection.disconnect();
				this.coronaLocationStats = coronaLocationStats;
				return null;
				
			}
			
			status = urlConnection.getResponseCode();
			if (status > 299) {
			    //streamReader = new InputStreamReader(con.getErrorStream());
				System.out.println("RESPONSE CODE: " + status);
			    in = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
			} else {
				System.out.println("RESPONSE CODE: " + status);
			    //streamReader = new InputStreamReader(con.getInputStream());
			    in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			    //System.out.println(in);
			    while ((inputLine = in.readLine()) != null) {
			        content.append(inputLine + "\n");
			    }
			    in.close();   
			}
			urlConnection.disconnect();
	}
		catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}
	
	
	
	public Iterable<CSVRecord>  converStringToCSVV1(String data) {
		System.out.println("Enter converStringToCSV");
		StringReader csvBodyReader = new StringReader(data);
		try {
			records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
				}
			catch (IOException e) {
				e.printStackTrace();
			}
		return records;
		
	}
	
	public List<CoronaLocationStat> getAllCountriesData(Iterable<CSVRecord> records) {
		System.out.println("Enter getAllCountriesData");
		List<CoronaLocationStat> coronaLocationStats = new ArrayList<CoronaLocationStat>();
		for (CSVRecord record : records) {

			CoronaLocationStat coronaLocationStat = new CoronaLocationStat();
		    String state = record.get("Province/State");
		    String country = record.get("Country/Region");
		    int totalCases = Integer.parseInt(record.get(record.size() - 1));
		    int latestCases = totalCases - Integer.parseInt(record.get(record.size() - 2));
		    coronaLocationStat.setState(state);
			coronaLocationStat.setCountry(country);
			coronaLocationStat.setLatestCases(latestCases);
			coronaLocationStat.setTotalCases(totalCases);
			//System.out.println(coronaLocationStat);
			coronaLocationStats.add(coronaLocationStat);
		
			}
		return coronaLocationStats;
		
		
	}
	
	public void getCountryStat(String country , String state) {
		int totalCases = 0;
		int latestCases = 0;
		System.out.println(records.toString());
		for (CSVRecord record : records) {
			System.out.println(record.toString());
	    	String country1 = record.get("Country/Region");
	        String state1 = record.get("Province/State");
	        if (country.equalsIgnoreCase(country1)) {
	        	System.out.println("country " + country + " found");
	        	if(state.equalsIgnoreCase(state1)) {
	        		totalCases = Integer.parseInt(record.get(record.size() - 1));
	    	        latestCases = totalCases - Integer.parseInt(record.get(record.size() - 2));
	        	}
	        }
	        
	    }
		System.out.println("Country: " + country + " State: " + state  + " cases today: " + latestCases + " total cases: " + totalCases);
	}
	
	@Override
	public String toString() {
		return "CoronaVirusDataService [coronaLocationStats=" + coronaLocationStats + "]";
	}

	public List<CoronaLocationStat> getCoronaLocationStats() {
		return coronaLocationStats;
	}

	public void setCoronaLocationStats(List<CoronaLocationStat> coronaLocationStats) {
		System.out.println("Enter setCoronaLocationStats");
		this.coronaLocationStats = coronaLocationStats;
		
	}
	
	
}
