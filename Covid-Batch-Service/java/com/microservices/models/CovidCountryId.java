package com.microservices.models;

import java.io.Serializable;
import java.util.Objects;

public class CovidCountryId implements  Serializable{

	private String country;
	private String province;
	
	
	
	public CovidCountryId() {
		super();
	}



	public CovidCountryId(String country, String province) {
		super();
		this.country = country;
		this.province = province;
	}



	@Override
	public boolean equals(Object arg0) {
		if (this == arg0) return true;
        if (arg0 == null || getClass() != arg0.getClass()) return false;
        CovidCountryId covidCountryId = (CovidCountryId) arg0;
        return country.equals(covidCountryId.country) &&
                province.equals(covidCountryId.province);
	}

	@Override
	public int hashCode() {
		return Objects.hash(country,province);
	}
	
	
	

}
