package com.coronavirus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientException;

public class CustomClientException extends WebClientException {
    private final HttpStatus status;
    private final String details;
    
    
	public CustomClientException(String msg, HttpStatus status, String details) {
		super(msg);
		this.status = status;
		this.details = details;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public String getDetails() {
		return details;
	}

    
    

}
