package com.microservices.models;

import java.util.List;


public class CovidDetailedData {
	
	
	private String province;
	private String country;
	private String lat;
	private String lon;
	private List<Integer> covidcount;
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public List<Integer> getCovidcount() {
		return covidcount;
	}
	public void setCovidcount(List<Integer> covidcount) {
		this.covidcount = covidcount;
	}
	
	
	public CovidDetailedData(String province, String country, String lat, String lon, List<Integer> covidcount) {
		super();
		this.province = province;
		this.country = country;
		this.lat = lat;
		this.lon = lon;
		this.covidcount = covidcount;
	}
	@Override
	public String toString() {
		return "CovidDetailedData [province=" + province + ", country=" + country + ", lat=" + lat + ", lon=" + lon
				+ ", covidcount=" + covidcount + "]";
	}
	
	
	
	

}
