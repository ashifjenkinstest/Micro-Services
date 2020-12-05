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
//@Scope
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
	/*
	private ExecutionContext context;
	
	private StepExecution stepExecution;
	
	private static final String[] OVERRIDDEN_BY_EXPRESSION = null;
	
	//@Value("#{jobParameters['csvHeader']}")
	private String[] header;
	
	

	JobParameters parameters = null;//stepExecution.getJobExecution().getJobParameters();
	
	
	
	
	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "ReadDataFromCSVFile [header=" + Arrays.toString(header) + "]";
	}

	@BeforeStep
	//@Deprecated
	public void beforeReadDataFromCSVFile(StepExecution stepExecution) {
		System.out.println();
		System.out.println("Enter beforeReadDataFromCSVFileInternal");
		this.context = stepExecution.getJobExecution().getExecutionContext();
		this.parameters = stepExecution.getJobExecution().getJobParameters();
		
		String[] addd = (String[]) this.context.get("csvHeader");
		System.out.println(addd.length);
		this.stepExecution = stepExecution;
		
		//this.header = (String[]) stepExecution.getJobExecution().getExecutionContext().get("csvHeader");
		setHeader((String[]) stepExecution.getJobExecution().getExecutionContext().get("csvHeader"));
		System.out.println(getHeader());
		System.out.println(toString());
		System.out.println("Exit beforeReadDataFromCSVFileInternal");
		
		
	}
	
	*/
	
	@Bean
	@StepScope
	public FlatFileItemReader<CovidDetailedData>  flatFileItemReaderInternal(String[] header){
		System.out.println("Enter flatFileItemReaderInternal");
	
		System.out.println("Getting header");
		/*
		testMyStepListener.getHeader();
		
		*/
		if(header!=null)
		{
			for (String string : header) {
				System.out.print(string+  ", ");
				System.out.println("header length " + header.length);
			}
			
		}
		else {
			System.out.println("Header is null");
		}
		
		System.out.println("Exit flatFileItemReaderInternal");
		return readDataFromCSVFileImpl.flatFileItemReader(header);
				
				
	}
	
	
	
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


	/*

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("Enter beforeStep");
		if(stepExecution ==null) {
			System.out.println("stepExecution is null");
		}else {
			System.out.println(((String[]) stepExecution.getJobExecution().getExecutionContext().get("csvHeader")).length);
			this.header = (String[]) stepExecution.getJobExecution().getExecutionContext().get("csvHeader");
			System.out.println(this.header);
			
		}
		System.out.println("Exit beforeStep");
	
	}

	@BeforeRead
	public void bread() {
		System.out.println("Enter bread");
		System.out.println("Exit bread");
		
	}
	
*/
	
	
}
