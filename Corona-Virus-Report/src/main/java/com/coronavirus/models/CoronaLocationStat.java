package com.coronavirus.models;

public class CoronaLocationStat {
	private int id;
	private String state ;
    private String country ;
    private int totalCases;
    private int latestCases;
    
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestCases() {
		return latestCases;
	}
	public void setLatestCases(int latestCases) {
		this.latestCases = latestCases;
	}
	@Override
	public String toString() {
		return "CoronaLocationStat [state=" + state + ", country=" + country + ", latestCases=" + latestCases + "]";
	}
	    
    

}
