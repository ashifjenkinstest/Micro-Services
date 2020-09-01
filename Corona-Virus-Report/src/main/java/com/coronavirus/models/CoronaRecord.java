package com.coronavirus.models;

import org.apache.commons.csv.CSVRecord;

public class CoronaRecord {
	
	private Iterable<CSVRecord>  csvRecord;

	public Iterable<CSVRecord> getCsvRecord() {
		return csvRecord;
	}

	public void setCsvRecord(Iterable<CSVRecord> csvRecord) {
		this.csvRecord = csvRecord;
	}
	
	
	
	

}
