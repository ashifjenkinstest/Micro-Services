package com.coronavirus.models;

public class CoronaLocationStat {
	private int id;
	private String state ;
    private String country ;
    private int totalCases;
    private int latestCases;
    private String status;
    
    
    
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CoronaLocationStat [id=" + id + ", state=" + state + ", country=" + country + ", totalCases="
				+ totalCases + ", latestCases=" + latestCases + ", status=" + status + "]";
	}
	
	

}
