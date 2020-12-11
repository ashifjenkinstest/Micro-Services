package com.microservices.listeners;

import org.springframework.batch.core.ItemProcessListener;

import com.microservices.models.CovidDetailedData;

public class MyStepItemProcessListener implements ItemProcessListener<CovidDetailedData, CovidDetailedData> {
 
    @Override
    public void beforeProcess(CovidDetailedData item) {
        System.out.println("Before process starts");
    }
 
    @Override
    public void afterProcess(CovidDetailedData item, CovidDetailedData result) {
        System.out.println("After process ends");
    }
 
    @Override
    public void onProcessError(CovidDetailedData item, Exception e) {
        System.out.println("Exception on processing");
    }
}