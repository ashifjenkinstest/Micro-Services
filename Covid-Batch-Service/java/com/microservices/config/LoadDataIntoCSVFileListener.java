package com.microservices.config;


import javax.batch.runtime.context.StepContext;

import org.aspectj.lang.annotation.Before;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.microservices.models.CovidCsvHeader;
import com.microservices.utils.LoadDataFromUri;
@Configuration
public class LoadDataIntoCSVFileListener {
	
	
	
	@BeforeStep
	public void beforeLoadDataIntoCSVFile(StepExecution stepExecution) {
		System.out.println();
		System.out.println("Enter beforeLoadDataIntoCSVFile");
		System.out.println("Exit beforeLoadDataIntoCSVFile");
		
		
	}
	
	@AfterStep
	public void afterLoadDataIntoCSVFile(StepExecution stepExecution) {
		System.out.println();
		System.out.println("Enter afterLoadDataIntoCSVFile");
		/*
		//stepExecution.getJobExecution().getExecutionContext().put("csvHeader",dataFromUri.getHeaderLine());
		String[] csvHeader = (String[]) stepExecution.getJobExecution().getExecutionContext().get("csvHeader");
		//covidCsvHeader.setHeaderLine(csvHeader);
		for (String string : csvHeader) {
			//System.out.println(string);
		}
		*/
		System.out.println("Exit afterLoadDataIntoCSVFile");
		
	}

}
