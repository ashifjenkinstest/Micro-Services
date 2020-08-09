package com.coronavirus.garbage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;

import com.coronavirus.models.CoronaLocationStat;

public class Garbage {
	
	
	private static final String CORONA_VIRUS_URL = null;
	private List<CoronaLocationStat> coronaLocationStats;
	Iterable<CSVRecord> records;
	/**
	 * @deprecated
	 */
	@PostConstruct
	@Scheduled(cron = "1 * * * * *")
	public void getVirusData()  {
		
		List<CoronaLocationStat> coronaLocationStats = new ArrayList<CoronaLocationStat>();
		int status = 0;
		System.out.println("Enter getVirusData");
		URL url = null;
		try {
			 url = new URL(CORONA_VIRUS_URL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
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
			try {
				status = urlConnection.getResponseCode();
			} catch (Exception e) {
				urlConnection.disconnect();
				this.coronaLocationStats = coronaLocationStats;
				return;
				
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
			    StringReader csvBodyReader = new StringReader(content.toString());

			    //Iterable<CSVRecord> records;
				try {
					records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
					
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
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//System.out.println(content);
			urlConnection.disconnect();
			this.coronaLocationStats = coronaLocationStats;
			

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	/**
	 * @deprecated
	 * @param data
	 * @return
	 */
	public List<CoronaLocationStat> converStringToCSV(String data) {
		System.out.println("Enter converStringToCSV");
		List<CoronaLocationStat> coronaLocationStats = new ArrayList<CoronaLocationStat>();
		StringReader csvBodyReader = new StringReader(data);
		try {
			records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return coronaLocationStats;
		
	}
	
	

}
