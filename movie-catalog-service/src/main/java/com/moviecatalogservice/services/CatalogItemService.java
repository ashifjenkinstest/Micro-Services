package com.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviecatalogservice.models.CatalogItem;
import com.moviecatalogservice.models.Movie;
import com.moviecatalogservice.models.MovieRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@Service
public class CatalogItemService {
	
	@Autowired
	private WebClient.Builder builder;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem", commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
					/*Wait for 2 second if no response then timeout.*/,
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6")
					/*The number of requests it will send.*/,
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
					/*Percentage of last failed requests, If 3 out of last 6 requests sent fails then fallback will be called.*/,
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
					/*Wait for 5 seconds before sending the  next set of requests.*/,
            })
		public CatalogItem getCatalogItem(MovieRating movieRating){
		System.out.println("Enter getCatalogItem");
		System.out.println("movieRating " + movieRating);
		Movie movie = builder
				.build()
				.get()
				.uri("http://movie-info-service/movies/"+ movieRating.getMovieId())
				.retrieve()
				.bodyToMono(Movie.class)
				.block();//We are blocking the execution till the mono  is finished.	
		System.out.println("Service call result [movie] " + movie);
		return new CatalogItem(Integer.parseInt(movie.getMovieId()),movie.getName(), movie.getMovieOverview(), movieRating.getRating());
		
	}
	
	public CatalogItem getFallbackCatalogItem(MovieRating movieRating){
		System.out.println("Enter getFallbackCatalogItem");
		System.out.println("Exit getFallbackCatalogItem");
		return new CatalogItem(Integer.parseInt(movieRating.getMovieId()),"No movie found ,INVALID", "", movieRating.getRating());
		
		
	}


}
