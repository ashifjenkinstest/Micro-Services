package com.microservices.config;


import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ReadDataFromCSVFileListener {
	
		
	@BeforeStep
	public void beforeReadDataFromCSVFile(StepExecution stepExecution) {
		System.out.println();
		System.out.println("Enter beforeReadDataFromCSVFile");
		/*
		String[] csvHeader = (String[]) stepExecution.getJobExecution().getExecutionContext().get("csvHeader");
		for (String string : csvHeader) {
			System.out.print(string);
		}
		System.out.println();
		*/
		System.out.println("Exit beforeReadDataFromCSVFile");
		
		
	}
	
	@AfterStep
	public void afterReadDataFromCSVFile(StepExecution stepExecution) {
		System.out.println();
		//stepExecution.getJobExecution().getExecutionContext().put("csvHeader",new int[]{1,2,3}/*dataFromUri.getHeaderLine()*/);
		String[] csvHeader = (String[]) stepExecution.getJobExecution().getExecutionContext().get("csvHeader");
		
		for (String string : csvHeader) {
			//System.out.println(string);
		}
		
		System.out.println("afterReadDataFromCSVFile");
		
	}

}
