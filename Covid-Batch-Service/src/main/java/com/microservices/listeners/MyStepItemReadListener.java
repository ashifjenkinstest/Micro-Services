package com.microservices.listeners;

import org.springframework.batch.core.ItemReadListener;

import com.microservices.models.CovidDetailedData;

public class MyStepItemReadListener implements ItemReadListener<CovidDetailedData> {
 
    @Override
    public void beforeRead() {
        System.out.println("Before read starts");
    }
 
    @Override
    public void afterRead(CovidDetailedData item) {
        System.out.println("After read ends");
    }
 
    @Override
    public void onReadError(Exception ex) {
        System.out.println("Exception on reading");
    }
}