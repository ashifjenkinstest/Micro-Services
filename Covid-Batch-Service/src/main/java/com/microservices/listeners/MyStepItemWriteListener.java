package com.microservices.listeners;

import java.util.List;
import org.springframework.batch.core.ItemWriteListener;
 
public class MyStepItemWriteListener implements ItemWriteListener<Number> {
 
    @Override
    public void beforeWrite(List<? extends Number> items) {
        System.out.println("Before write starts");
    }
 
    @Override
    public void afterWrite(List<? extends Number> items) {
        System.out.println("After write ends");
    }
 
    @Override
    public void onWriteError(Exception exception, List<? extends Number> items) {
        System.out.println("Exception on writting");
    }
}
