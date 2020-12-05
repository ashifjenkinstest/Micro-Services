package com.microservices.steps;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import javax.batch.api.chunk.ItemWriter;
import javax.batch.runtime.JobExecution;
import javax.sql.DataSource;

import org.aspectj.lang.annotation.Before;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
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
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

import com.microservices.config.LoadDataIntoCSVFileListener;
import com.microservices.config.ReadDataFromCSVFileListener;
import com.microservices.listeners.CSVReaderStepListener;
import com.microservices.mappers.CovidDetailedDataMapper;
import com.microservices.models.CovidCsvHeader;
import com.microservices.models.CovidDetailedData;
import com.microservices.utils.LoadDataFromUri;


@Configuration
@ComponentScan(basePackages = "com.microservices")
@Component
//@Scope
public class ReadDataFromCSVFileImpl {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	LoadDataFromUri loadDataFromUri;

	@Autowired
	private CSVReaderStepListener testMyStepListener;
	
	@Value("${csv_data_dir}")
	private String csv_data_dir;
	
	
	@Bean
	@StepScope
	//@JobScope
	public FlatFileItemReader<CovidDetailedData>  flatFileItemReader(String[] header){
		System.out.println("Enter flatFileItemReader");
		
		FlatFileItemReader<CovidDetailedData> fileItemReader  = new FlatFileItemReader<CovidDetailedData>();
		fileItemReader.setLinesToSkip(1);
		//new PathResource
		System.out.println("Dynamic File Name " + testMyStepListener.getCsvFileName());
		fileItemReader.setStrict(true);
		
		File directory = new File(csv_data_dir.concat("/".concat(testMyStepListener.getCsvFileName())));
		System.out.println(directory.getAbsolutePath());
		System.out.println("Exists " + directory.exists());
		
		
		//fileItemReader.setResource(new ClassPathResource("Data/Covid_051220208478227628523852485.csv"));
		fileItemReader.setResource(new PathResource(csv_data_dir.concat("/".concat(testMyStepListener.getCsvFileName()))));
		DefaultLineMapper<CovidDetailedData> defaultLineMapper = new DefaultLineMapper<CovidDetailedData>();
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
		delimitedLineTokenizer.setNames(testMyStepListener.getHeader());
		
		
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(new CovidDetailedDataMapper());
		defaultLineMapper.afterPropertiesSet();
		
		fileItemReader.setLineMapper(defaultLineMapper);
		System.out.println("Exit flatFileItemReader");
		return fileItemReader;
		
	}
	


	@Bean
	public org.springframework.batch.item.ItemWriter<CovidDetailedData> localItemWriter(){
		System.out.println("Enter localItemWriter");
		System.out.println("Exit localItemWriter");
		return items -> {
            for(CovidDetailedData item : items ){
               System.out.println(item.toString());

            }
        };
	}


	
	
}
