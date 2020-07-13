package com.moviecatalogservice.models;

import java.util.List;

public class UserRating {
	
	private List<MovieRating> movieRatings;

	
	public UserRating() {
		super();
	}
	
	public UserRating(List<MovieRating> movieRatings) {
		super();
		this.movieRatings = movieRatings;
	}

	
	
	public List<MovieRating> getMovieRatings() {
		return movieRatings;
	}

	public void setMovieRatings(List<MovieRating> movieRatings) {
		this.movieRatings = movieRatings;
	}



}
