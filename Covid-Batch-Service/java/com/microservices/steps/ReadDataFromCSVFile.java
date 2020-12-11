package com.microservices.steps;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.microservices.listeners.CSVReaderStepListener;
import com.microservices.models.CovidDetailedData;
import com.microservices.utils.LoadDataFromUri;


@Configuration
@ComponentScan(basePackages = "com.microservices")
@Component
public class ReadDataFromCSVFile  {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	LoadDataFromUri loadDataFromUri;

	@Autowired
	private ReadDataFromCSVFileImpl readDataFromCSVFileImpl;
	
	@Autowired
	private CSVReaderStepListener testMyStepListener;
		
	@Bean
	public Step readDataFromCSVFileStep() {
		System.out.println("Enter readDataFromCSVFileStep");
		System.out.println("Exit readDataFromCSVFileStep");
		return stepBuilderFactory.get("flatFileReaderStep")
				.//listener(new MyStepListener())//add your Listener here
				//.listener(new ReadDataFromCSVFile())
				listener(testMyStepListener)
				.<CovidDetailedData,CovidDetailedData>chunk(100)
				//.listener(new MyStepItemReadListener())//add your Listener here
				.reader(readDataFromCSVFileImpl.flatFileItemReader())
				//.listener(new MyStepItemReadListener())//add your Listener here
				//.writer(readDataFromCSVFileImpl.localItemWriter())
				.writer(readDataFromCSVFileImpl.compositeItemWriter())
				.faultTolerant()
				.build();
		
	}
	
	
}
