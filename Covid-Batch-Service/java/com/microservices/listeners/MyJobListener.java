package com.microservices.listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
 
public class MyJobListener implements JobExecutionListener {
 
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before job starts");
    }
 
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After job ends.");
    }
}
