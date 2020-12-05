package com.microservices.steps;

import java.util.Map;



import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.microservices.config.LoadDataIntoCSVFileListener;
import com.microservices.listeners.CSVReaderStepListener;
import com.microservices.utils.LoadDataFromUri;

@Configuration
@Component
@ComponentScan(basePackages = "com.microservices")
public class LoadDataIntoCSVFile  /*implements StepExecutionListener */{
	

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	

	private StepExecution stepExecution;
	
	
	@Autowired
	private LoadDataFromUri dataFromUri;
	
	@Bean
	public Tasklet loadCSVDataStepTasklet() {
		System.out.println("Enter loadCSVDataStepTasklet");
		return new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				
				dataFromUri.writeToCSVFile(dataFromUri.converStringToCSVV1(dataFromUri.getDataFromGit()));
				
				chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("csvHeader",dataFromUri.getHeaderLine());
				chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("csvFileName",dataFromUri.getFileName());
				System.out.println("Exit loadCSVDataStepTasklet");
				return RepeatStatus.FINISHED;
				
			};
		};
		
	}
	
		
	@Bean
	public Step loadCSVDataStep() {
		System.out.println("Enter loadCSVDataStep");
		System.out.println("Exit loadCSVDataStep");
		return stepBuilderFactory.get("loadCSVDataStep")
				//.listener(new MyStepListener())
				//.listener(new LoadDataIntoCSVFile())
				.tasklet(loadCSVDataStepTasklet())
				.build();
		
	}



}
