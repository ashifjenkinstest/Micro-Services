package com.coronavirus.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.coronavirus.models.CoronaCountryDataStat;
import com.coronavirus.models.CoronaLocationStat;

@Service
public class CoronaVirusDataService {

	private static final String CORONA_VIRUS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	private List<CoronaLocationStat> coronaLocationStats;
	CoronaCountryDataStat coronaCountryDataStat;
	Iterable<CSVRecord> records;

	
	/*Below you can find the example patterns from the spring forum:

	 // "0 0 * * * *" = the top of every hour of every day.
	 // "*(/)10 * * * * *" = every ten seconds. remove ()
	 // "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
	 // "0 0 8,10 * * *" = 8 and 10 o'clock of every day.
	 // "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
	 // "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
	 // "0 0 0 25 12 ?" = every Christmas Day at midnight
	 // Cron expression is represented by six fields:
	 // second, minute, hour, day of month, month, day(s) of week
	 // (*) means match any

	 // *(/)X means "every X"

	 //? ("no specific value") - useful when you need to specify something in one of the two fields in which the character is allowed, but not the other.
	  *  For example, if I want my trigger to fire on a particular day of the month (say, the 10th), 
	  *  but I don't care what day of the week that happens to be, I would put "10" in the day-of-month field and "?" in the day-of-week field.
	*/
		
	
	
	@PostConstruct
	@Scheduled(cron = "0 0 * * * *")
	public void getVirusDataV1() {
		System.out.println("Enter getVirusDataV1");
		setCoronaLocationStats(getAllCountriesData(converStringToCSVV1(getDataFromGit())));
		System.out.println("Exit getVirusDataV1");
	}

	
	/**
	 * Loads the URL and returns the content of the URL .
	 * @return  String
	 */
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
				this.coronaLocationStats = coronaLocationStats;
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
		System.out.println("Enter converStringToCSV");
		if (data!=null) {
			
			StringReader csvBodyReader = new StringReader(data);
			try {
				records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return records;
			
		}
		return null;
		

	}

	public List<String> getVirusDataHeader(String data) {
		System.out.println("Enter getVirusDataHeader");
		Map<String, Integer> headerMap = new HashMap<String, Integer>();
		List<String> csvHeaderDates = new ArrayList<String>();
		try {
			// records =
			// CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord(false).parse(csvBodyReader);
			CSVParser parser = CSVParser.parse(data, CSVFormat.EXCEL.withFirstRecordAsHeader());
			headerMap = parser.getHeaderMap();
			headerMap.remove("Province/State");
			headerMap.remove("Country/Region");
			headerMap.remove("Lat");
			headerMap.remove("Long");
			for (Map.Entry<String, Integer> entry : headerMap.entrySet()) {
				// System.out.println("Key = " + entry.getKey() + ", Value = " +
				// entry.getValue());
				csvHeaderDates.add(entry.getKey());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return csvHeaderDates;

	}

	/**
	 * Returns list of CoronaLocationStat from CSV records
	 * @param Iterable<CSVRecord>
	 * @return List<CoronaLocationStat>
	 */
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
			// System.out.println(coronaLocationStat);
			coronaLocationStats.add(coronaLocationStat);
		}
		return coronaLocationStats;
	}

	public CoronaCountryDataStat getCountryStat(String country, String state) {

		this.coronaCountryDataStat = getCountryStatImpl(country, state, converStringToCSVV1(getDataFromGit()));
		return this.coronaCountryDataStat;
	}

	public CoronaCountryDataStat getCountryStatImpl(String country, String state, Iterable<CSVRecord> records) {
		CoronaCountryDataStat coronaCountryDataStat = new CoronaCountryDataStat();
		// Map<String, Integer> dateVsCount = new HashMap<String, Integer>();
		Map<Integer, Integer> dateVsCount = new HashMap<Integer, Integer>();
		System.out.println("Enter getCountryStatImpl");
		System.out.println("country: " + country);
		System.out.println("state: " + state);

		int totalCases = 0;
		int latestCases = 0;
		if(records == null) {
			System.out.println("Got records null " +coronaCountryDataStat.toString());
			return coronaCountryDataStat;
		}
		for (CSVRecord record : records) {
			String country1 = record.get("Country/Region");
			String state1 = record.get("Province/State");
			if (country.equalsIgnoreCase(country1)) {

				if (state.equalsIgnoreCase(state1)) {
					// System.out.println("country " + country + " found");
					coronaCountryDataStat.setCountry(country1);
					coronaCountryDataStat.setState(state1);
					for (int i = 4; i < record.size(); i++) {
						// Map date to count
						// System.out.print(header.get(i-4)+ " -->"+ record.get(i)+ ", ");
						dateVsCount.put(/* header.get */(i - 4), Integer.parseInt(record.get(i)));
					}
					coronaCountryDataStat.setDateVsCount(dateVsCount);
					totalCases = Integer.parseInt(record.get(record.size() - 1));
					latestCases = totalCases - Integer.parseInt(record.get(record.size() - 2));
					coronaCountryDataStat.setLatestCases(latestCases);
					coronaCountryDataStat.setTotalCases(totalCases);
				}
			}

		}
		System.out.println("Country: " + country + " State: " + state + " cases today: " + latestCases
				+ " total cases: " + totalCases);
		return coronaCountryDataStat;

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
		System.out.println("Exit setCoronaLocationStats");

	}

}
