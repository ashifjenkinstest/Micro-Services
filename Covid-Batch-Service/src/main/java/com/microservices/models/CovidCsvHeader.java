package com.microservices.models;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public class CovidCsvHeader {
	
	private String headerLine[];
	
	
	
	public CovidCsvHeader(String[] headerLine) {
		super();
		this.headerLine = headerLine;
	}

	public String[] getHeaderLine() {
		return headerLine;
	}


	
	public void  setHeaderLine(String[] headerLine) {
		this.headerLine = headerLine;
	
	}

	@Override
	public String toString() {
		return "CovidCsvHeader [headerLine=" + Arrays.toString(headerLine) + "]";
	}
	
	


}
