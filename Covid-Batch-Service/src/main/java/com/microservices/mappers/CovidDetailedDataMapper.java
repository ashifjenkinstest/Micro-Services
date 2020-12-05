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
		return new CovidDetailedData(
				fieldSet.readString("Province/State"),
				fieldSet.readString("Country/Region"),
				fieldSet.readString("Lat"),
				fieldSet.readString("Long"),
				dayCount
				);
	}

}
