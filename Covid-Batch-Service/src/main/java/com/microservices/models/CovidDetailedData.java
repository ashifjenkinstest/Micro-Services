package com.microservices.models;

import java.util.List;

public class CovidDetailedData {
	
	
	private String provinceState;
	private String countryRegion;
	private String lat;
	private String lon;
	private List<Integer> dayCount;
	public CovidDetailedData(String provinceState, String countryRegion, String lat, String lon,
			List<Integer> dayCount) {
		super();
		this.provinceState = provinceState;
		this.countryRegion = countryRegion;
		this.lat = lat;
		this.lon = lon;
		this.dayCount = dayCount;
	}
	@Override
	public String toString() {
		return "CovidDetailedData [provinceState=" + provinceState + ", countryRegion=" + countryRegion + ", lat=" + lat
				+ ", lon=" + lon + ", dayCount=" + dayCount + "]";
	}
	
	
	
	
	
	
	
	
	

}
