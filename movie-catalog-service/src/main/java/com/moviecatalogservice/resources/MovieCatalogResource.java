package com.moviecatalogservice.resources;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviecatalogservice.models.CatalogItem;
import com.moviecatalogservice.models.Movie;
import com.moviecatalogservice.models.MovieRating;
import com.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private WebClient.Builder builder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId")String userId){
		
		
		List<MovieRating> movieRatings = Arrays.asList(
				new MovieRating("1234", 5),
				new MovieRating("5678", 4)
				); 
		
		return movieRatings.stream().map(rating ->{
			Movie movie =   restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Transformers is a series of American science fiction action films based "
					+ "on the Transformers franchise which began in the 1980s.[note 1] Michael Bay has directed the "
					+ "first five films: Transformers (2007), Revenge of the Fallen (2009), Dark of the Moon (2011), "
					+ "Age of Extinction (2014) and The Last Knight (2017).[1][2][3] A spin-off film, Bumblebee, directed "
					+ "by Travis Knight and produced by Bay, was released on December 21, 2018.", rating.getRating())	;	
		}
		).collect(Collectors.toList());
		
	}
	
	
	@RequestMapping("/{userId}/v1")
	public List<CatalogItem> getCatalogv1(@PathVariable("userId")String userId){
		
		UserRating movieRatings = restTemplate.getForObject("http://movie-rating-service/rating/users/" + userId, UserRating.class);
		
		return movieRatings.getMovieRatings().stream().map(rating ->{
			Movie movie = builder
					.build()
					.get()
					.uri("http://movie-info-service/movies/"+ rating.getMovieId())
					.retrieve()
					.bodyToMono(Movie.class)
					.block();//We are blocking the execution till the mono  is finished.	
			return new CatalogItem(movie.getName(), "Transformers is a series of American science fiction action films based "
					+ "on the Transformers franchise which began in the 1980s.[note 1] Michael Bay has directed the "
					+ "first five films: Transformers (2007), Revenge of the Fallen (2009), Dark of the Moon (2011), "
					+ "Age of Extinction (2014) and The Last Knight (2017).[1][2][3] A spin-off film, Bumblebee, directed "
					+ "by Travis Knight and produced by Bay, was released on December 21, 2018.", rating.getRating())	;	
		}
		).collect(Collectors.toList());
		
	}
	
}
