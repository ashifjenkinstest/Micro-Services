package com.microservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.microservices.models.CovidDetailedData;

public class CovidDetailedDataMapper implements FieldSetMapper<CovidDetailedData>{

	@Override
	public CovidDetailedData mapFieldSet(FieldSet fieldSet) throws BindException {
		//System.out.println("Enter mapFieldSet");
		List<Integer> dayCount = new ArrayList<Integer>();
		//System.out.println(fieldSet.getFieldCount());
		for(int i=5;i<=fieldSet.getFieldCount()-1;i++) {
			dayCount.add(fieldSet.readInt(i));
		}
		//System.out.println("Exit mapFieldSet");
		String prov = "";
		String lati = "";
		String longi = "";
		if(fieldSet.readString("Province/State") == null || fieldSet.readString("Province/State").length()==0) {
			prov = "NULL-PROV";
		}else {
			prov = fieldSet.readString("Province/State");
		}
		//System.out.println("PROV" + prov);
		if(fieldSet.readString("Lat") == null || fieldSet.readString("Lat").length()==0) {
			lati = "NULL-LAT";
		}else {
			lati = fieldSet.readString("Lat");
		}
		if(fieldSet.readString("Long") == null || fieldSet.readString("Long").length()==0) {
			longi = "NULL-LONG";
		}else {
			longi = fieldSet.readString("Long");
		}
		
		return new CovidDetailedData(
				prov,
				fieldSet.readString("Country/Region"),
				lati,
				longi,
				dayCount
				);
	}

}
