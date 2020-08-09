package com.moviecatalogservice.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviecatalogservice.models.MovieRating;
import com.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@Service
public class UserRatingService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//private UserRating userRating;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
			/*Wait for 2 second if no response then timeout.*/,
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6")
			/*The number of requests it will send.*/,
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
			/*Percentage of last failed requests, If 3 out of last 6 requests sent fails then fallback will be called.*/,
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			/*Wait for 5 seconds before sending the  next set of requests.*/,
    })
	public UserRating getUserRating(String userId) {
		System.out.println("Enter getUserRating");
		/*If this fallback method is part the main class from where its being called then
		 * the proxy class will not be able to call this method during fallback.
		 * Because the method it being called from another method.
		 * Seems like the Spring does not know that the method should be called when there
		 * is a fall back.
		 * In order to fix this issue we need to make the method and its fallback method
		 * part of a different service.
		 * java.lang.IllegalStateException: No instances available for movie-rating-service
		 * 
		 * 
		 * */
		
		UserRating userRating  = restTemplate.getForObject("http://movie-rating-service/rating/users/" + userId, UserRating.class);
		System.out.println("getUserRating " + userRating);
		System.out.println("Exit getUserRating");
		return  userRating ;
	}
	
	
	public UserRating getFallbackUserRating(String userId) {
		System.out.println("Enter getFallbackUserRating");
		UserRating userRating1 = new UserRating();
		/*
		List<MovieRating> movieRatings= new ArrayList<MovieRating>();
		if(userRating!= null && userRating.getMovieRatings()!= null && userRating.getMovieRatings().size()>0) {
			for (MovieRating mr : userRating.getMovieRatings()) {
				movieRatings.add(mr);
			}
		}else {
			movieRatings.add(new MovieRating("0",0));
		}
		
		userRating1.setMovieRatings(movieRatings);*/
		userRating1.setMovieRatings(Arrays.asList(new MovieRating("0",0)));
		System.out.println(userRating1);
		System.out.println("Exit getFallbackUserRating");
		return userRating1;
	}


	
	

}
