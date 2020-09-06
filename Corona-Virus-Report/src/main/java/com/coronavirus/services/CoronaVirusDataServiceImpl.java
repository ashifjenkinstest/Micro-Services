package com.coronavirus.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.coronavirus.models.CoronaCountryDataStat;
import com.coronavirus.models.CoronaLocationStat;
import com.coronavirus.repositories.AllCountriesDataRepository;
import com.coronavirus.repositories.CountryDataRepository;
import com.coronavirus.utils.LoadDataFromUrl;

@Service
public class CoronaVirusDataServiceImpl {
	
	private String dataFromGitURL;
	private CoronaCountryDataStat coronaCountryDataStat;
	private List<CoronaLocationStat> coronaLocationStats;
	private Iterable<CSVRecord>  csvCoronaRecord;
	
	@Autowired
	AllCountriesDataService allCountriesDataService;
	
	@Autowired
	private LoadDataFromUrl dataFromUrl;

	public CoronaVirusDataServiceImpl() {
		System.out.println("Called CoronaVirusDataServiceImpl");
	}
	
	@PostConstruct
	@Scheduled(cron = "0 0 0 * * *")
	private void getVirusDataV1() {
		
		System.out.println("Enter getVirusDataV1");
		setDataFromGitURL(dataFromUrl.getDataFromGit());
		
		setCsvCoronaRecord(/*recoIterable*/ dataFromUrl.converStringToCSVV1(getDataFromGitURL()));
		if(getCsvCoronaRecord() != null) {
		setCoronaLocationStats(getAllCountriesData(getCsvCoronaRecord()));
		allCountriesDataService.insertAllCountriesData(getCoronaLocationStats());
		}
		System.out.println("Exit getVirusDataV1");
		
		
	}
	
	
	
	/**
	 * Returns list of CoronaLocationStat from CSV records
	 * @param Iterable<CSVRecord>
	 * @return List<CoronaLocationStat>
	 */
	public List<CoronaLocationStat> getAllCountriesData(Iterable<CSVRecord> records) {
		
		System.out.println("Enter getAllCountriesDataNEW");
		List<CoronaLocationStat> coronaLocationStats = new ArrayList<CoronaLocationStat>();
		
		int i = 0;
		for (CSVRecord record : records) { i++;
			CoronaLocationStat coronaLocationStat = new CoronaLocationStat();
			
			String state = record.get("Province/State");
			String country = record.get("Country/Region");
			int totalCases = Integer.parseInt(record.get(record.size() - 1));
			int latestCases = totalCases - Integer.parseInt(record.get(record.size() - 2));
			coronaLocationStat.setId(i);
			coronaLocationStat.setState(state);
			coronaLocationStat.setCountry(country);
			coronaLocationStat.setLatestCases(latestCases);
			coronaLocationStat.setTotalCases(totalCases);
			
			coronaLocationStats.add(coronaLocationStat);
		}
		return coronaLocationStats;
	}
	
	/**
	 * Returns country and state specific data
	 * @param Iterable<CSVRecord>
	 * @return List<CoronaLocationStat>
	 */
	
	public CoronaCountryDataStat getCountryStatImpl(String country, String state) {
		
		CoronaCountryDataStat coronaCountryDataStat = new CoronaCountryDataStat();
		Map<Integer, Integer> dateVsCount = new HashMap<Integer, Integer>();
		System.out.println("Enter getCountryStatImplNEW");
		System.out.println("country: " + country);
		System.out.println("state: " + state);

		int totalCases = 0;
		int latestCases = 0;
		if(getCsvCoronaRecord() == null) {
			System.out.println("Got records null " +coronaCountryDataStat.toString());
			return coronaCountryDataStat;
		}
		Iterable<CSVRecord>  recoIterable = dataFromUrl.converStringToCSVV1(getDataFromGitURL());
		setCsvCoronaRecord(recoIterable);
		for (CSVRecord record : recoIterable) {
			String country1 = record.get("Country/Region");
			String state1 = record.get("Province/State");
			if (country.equalsIgnoreCase(country1)) {

				if (state.equalsIgnoreCase(state1)) {
					coronaCountryDataStat.setCountry(country1);
					coronaCountryDataStat.setState(state1);
					for (int i = 4; i < record.size(); i++) {
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
	
	public CoronaCountryDataStat getCountryStat(String country, String state) {

		setCoronaCountryDataStat(getCountryStatImpl(country, state));
		return getCoronaCountryDataStat();
	}

	public String getDataFromGitURL() {
		return dataFromGitURL;
	}

	public void setDataFromGitURL(String dataFromGitURL) {
		this.dataFromGitURL = dataFromGitURL;
	}

	public Iterable<CSVRecord> getCsvCoronaRecord() {
		
		return csvCoronaRecord;
	}

	public void setCsvCoronaRecord(Iterable<CSVRecord> csvCoronaRecord) {
		
		this.csvCoronaRecord = csvCoronaRecord;
	}

	public CoronaCountryDataStat getCoronaCountryDataStat() {
		return coronaCountryDataStat;
	}

	public void setCoronaCountryDataStat(CoronaCountryDataStat coronaCountryDataStat) {
		this.coronaCountryDataStat = coronaCountryDataStat;
	}

	public List<CoronaLocationStat> getCoronaLocationStats() {
		return coronaLocationStats;
	}

	public void setCoronaLocationStats(List<CoronaLocationStat> coronaLocationStats) {
		this.coronaLocationStats = coronaLocationStats;
	}

	public void showLoadedData() {
		System.out.println("Enter showLoadedData");
		int i = 0;
		for (CoronaLocationStat coronaLocationStat : getCoronaLocationStats()) {
			System.out.println(coronaLocationStat.toString());
			i++;
			if (i==5)
				break;
		}
		getCsvCoronaRecord();
	}
	public void showLoadedRecords() {
		System.out.println("Enter showLoadedRecords");
		int i = 0;
		for (CSVRecord csvRecord : getCsvCoronaRecord()) {
			System.out.println(csvRecord.toString());
			i++;
			if (i==5)
				break;
			
		}
	}
	
	
	
	
}
