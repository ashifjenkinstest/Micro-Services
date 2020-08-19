package com.coronavirus.models;

import java.util.Map;

public class CoronaCountryDataStat {
	
	private String country;
	private String state;
	private Map<Integer, Integer> dateVsCount;
	private int totalCases;
	private int latestCases;
	
	
	
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	public int getLatestCases() {
		return latestCases;
	}
	public void setLatestCases(int latestCases) {
		this.latestCases = latestCases;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Map<Integer, Integer> getDateVsCount() {
		return dateVsCount;
	}
	public void setDateVsCount(Map<Integer, Integer> dateVsCount) {
		this.dateVsCount = dateVsCount;
	}
	@Override
	public String toString() {
		return "CoronaCountryDataStat [country=" + country + ", state=" + state + ", dateVsCount=" + dateVsCount + "]";
	}
	
	
	

	
	

}
