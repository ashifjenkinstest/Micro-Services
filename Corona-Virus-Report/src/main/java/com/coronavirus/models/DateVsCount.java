package com.coronavirus.models;

public class DateVsCount {
	
	private int day;
	private float cases;
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public float getCases() {
		return cases;
	}
	public void setCases(float cases) {
		this.cases = cases;
	}
	@Override
	public String toString() {
		return "DateVsCount [day=" + day + ", cases=" + cases + "]";
	}
	
	
	

}
