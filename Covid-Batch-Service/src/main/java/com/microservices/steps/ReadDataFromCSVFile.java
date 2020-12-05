package com.microservices.steps;

import java.util.Arrays;
import java.util.Map;

import javax.batch.api.chunk.ItemWriter;
import javax.batch.runtime.JobExecution;
import javax.sql.DataSource;

import org.aspectj.lang.annotation.Before;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.microservices.config.LoadDataIntoCSVFileListener;
import com.microservices.config.ReadDataFromCSVFileListener;
import com.microservices.listeners.MyStepItemProcessListener;
import com.microservices.listeners.MyStepItemReadListener;
import com.microservices.listeners.CSVReaderStepListener;
import com.microservices.mappers.CovidDetailedDataMapper;
import com.microservices.models.CovidCsvHeader;
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
	public Step readDataFromCSVFileStep(String calledFrom) {
		System.out.println("Enter readDataFromCSVFileStep");
		System.out.println("Called from " + calledFrom);
		System.out.println("Exit readDataFromCSVFileStep");
		return stepBuilderFactory.get("flatFileReaderStep")
				.//listener(new MyStepListener())//add your Listener here
				//.listener(new ReadDataFromCSVFile())
				listener(testMyStepListener)
				.<CovidDetailedData,CovidDetailedData>chunk(100)
				//.listener(new MyStepItemReadListener())//add your Listener here
				.reader(readDataFromCSVFileImpl.flatFileItemReader(null))
				//.listener(new MyStepItemReadListener())//add your Listener here
				.writer(readDataFromCSVFileImpl.localItemWriter())
				.build();
		
	}
	
	
}
