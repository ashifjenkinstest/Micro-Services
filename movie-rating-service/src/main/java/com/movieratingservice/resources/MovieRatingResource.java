package com.movieratingservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieratingservice.models.MovieRating;
import com.movieratingservice.models.UserRating;

@RestController
@RequestMapping("/rating")
public class MovieRatingResource {

	@RequestMapping("/{movieId}")
	public MovieRating getMovieRating(@PathVariable("movieId")String movieId) {
		return new MovieRating(movieId, 4);
		
	}
	
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId")String userId) {
		List<MovieRating> movieRatings = Arrays.asList(
				new MovieRating("1234", 3),
				new MovieRating("5678", 4)
				);	
		UserRating userRating = new UserRating();
		userRating.setMovieRatings(movieRatings);
		return  userRating;
		
	}
	
}
