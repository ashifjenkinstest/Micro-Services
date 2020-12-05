package com.microservices.listeners;

import org.springframework.batch.core.SkipListener;

public class MyStepSkipListener implements SkipListener<String, Number> {
 
    @Override
    public void onSkipInRead(Throwable t) {
        System.out.println(" A failure on read");
    }
 
    @Override
    public void onSkipInWrite(Number item, Throwable t) {
        System.out.println("This item failed on write "+item);
    }
 
    @Override
    public void onSkipInProcess(String item, Throwable t) {
        System.out.println("This item failed on processing "+item);
    }
}