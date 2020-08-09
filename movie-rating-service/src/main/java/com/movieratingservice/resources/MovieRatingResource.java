package com.movieratingservice.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieratingservice.models.MovieRating;
import com.movieratingservice.models.UserRating;

@RestController
@RequestMapping("/rating")
public class MovieRatingResource {
	
	@Autowired
	private com.movieratingservice.utils.RandomUtil  rand;
	
	@RequestMapping("/{movieId}")
	public MovieRating getMovieRating(@PathVariable("movieId")String movieId) {
		return new MovieRating(movieId, 4);
		
	}
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId")String userId) {
		List<MovieRating> movieRatings = new ArrayList<MovieRating>();
		for(int i = 0;i<rand.getRandomRating(10)+1;i++) {
			movieRatings.add(new MovieRating(String.valueOf(rand.getRandomRating(200)) , rand.getRandomRating(10)));
		}
		
		/*
		List<MovieRating> movieRatings = Arrays.asList(
				new MovieRating(String.valueOf(rand.getRandomRating(200)) , rand.getRandomRating(10)),
				new MovieRating(String.valueOf(rand.getRandomRating(200)) , rand.getRandomRating(10))
				);
		*/		
		UserRating userRating = new UserRating();
		userRating.setMovieRatings(movieRatings);
		System.out.println(userRating.toString());
		return  userRating;
		
	}
	
}
