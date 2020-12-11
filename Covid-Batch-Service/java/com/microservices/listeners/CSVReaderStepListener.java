package com.microservices.listeners;

import java.util.Arrays;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
 
@Component
public class CSVReaderStepListener implements StepExecutionListener {

	private ExecutionContext context;
	
	private StepExecution stepExecution;
	
	private static final String[] OVERRIDDEN_BY_EXPRESSION = null;
	
	private String[] header;

	JobParameters parameters = null;
	
	
	private String csvFileName;

	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("Before step starts");	
		setHeader((String[]) stepExecution.getJobExecution().getExecutionContext().get("csvHeader"));
		setCsvFileName((String)stepExecution.getJobExecution().getExecutionContext().get("csvFileName"));
		
		System.out.println(toString());
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("After step ends.");
		
		return ExitStatus.COMPLETED;
	}
	
	
	
	@Bean
	public String getCsvFileName() {
		return csvFileName;
	}

	public void setCsvFileName(String csvFileName) {
		this.csvFileName = csvFileName;
	}

	@Bean
	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}



	@Override
	public String toString() {
		return "MyStepListener [header=" + Arrays.toString(header) + "\n,csvFileName=" + csvFileName + "]";
	}

	
	
	
}