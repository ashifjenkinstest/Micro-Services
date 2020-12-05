package com.microservices.jobs;

import javax.batch.runtime.StepExecution;

import org.springframework.batch.core.Job;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.microservices.listeners.MyJobListener;
import com.microservices.listeners.MyStepItemReadListener;
import com.microservices.listeners.CSVReaderStepListener;
import com.microservices.steps.LoadDataIntoCSVFile;
import com.microservices.steps.ReadDataFromCSVFile;

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = "com.microservices")
public class CovidBatchJob {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private LoadDataIntoCSVFile  loadCSVData;

	@Autowired
	private ReadDataFromCSVFile  readDataFromCSVFile;
	
	@Bean
	public Job CovidBatchJobImpl() {
		System.out.println("CovidBatchJobImpl");
		
		return jobBuilderFactory.get("CovidBatchJobImpl_306")
				//.listener(new MyStepItemReadListener())
				.start(loadCSVData.loadCSVDataStep())
				.on("COMPLETED")
				.to(readDataFromCSVFile.readDataFromCSVFileStep("Job")).end()
				.build();
		
		//https://stackoverflow.com/questions/6078009/how-to-get-access-to-job-parameters-from-itemreader-in-spring-batch
		
	}

	
	
}
