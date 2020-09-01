package com.coronavirus.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoadDataFromUrl {
	
	private static final String CORONA_VIRUS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	
	/**
	 * Loads the URL and returns the content of the URL .
	 * @return  String
	 */
	
	@SuppressWarnings("unused")
	public  String getDataFromGit() {
		System.out.println("Enter getDataFromGitNEW");
		int status = 0;
		URL url = null;
		try {
			url = new URL(CORONA_VIRUS_URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection urlConnection = null;
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
				//this.coronaLocationStats = coronaLocationStats;
				return null;

			}

			status = urlConnection.getResponseCode();
			if (status > 299) {
				// streamReader = new InputStreamReader(con.getErrorStream());
				System.out.println("RESPONSE CODE: " + status);
				in = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
			} else {
				System.out.println("RESPONSE CODE: " + status);
				// streamReader = new InputStreamReader(con.getInputStream());
				in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				// System.out.println(in);
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine + "\n");
				}
				in.close();
			}
			urlConnection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}
	
	/**
	 * Converts String to CSV
	 * @param String
	 * @return Iterable<CSVRecord>
	 */
	
	public Iterable<CSVRecord> converStringToCSVV1(String data) {
		System.out.println("Enter converStringToCSVNEW");
		if (data!=null) {
			
			StringReader csvBodyReader = new StringReader(data);
			CSVParser records = null;
			try {
				records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return records;
			
		}
		return null;
		

	}


}
