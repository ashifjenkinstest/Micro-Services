package com.coronavirus.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryCaseHistoryId  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("Date")
	private Date dateOfCase;
	
	@JsonProperty("Country")
	private String country;

	
	
	public CountryCaseHistoryId() {
		super();
	}



	public CountryCaseHistoryId(Date dateOfCase, String country) {
		super();
		this.dateOfCase = dateOfCase;
		this.country = country;
	}

	
}
