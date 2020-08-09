package com.movieinfoservice.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.movieinfoservice.models.Movie;
import com.movieinfoservice.models.MovieSummary;


@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{movieId}")
	public Movie getMovie(@PathVariable("movieId") String movieId) {
		System.out.println(restTemplate.toString());
		
		System.out.println("apiKey: "+ apiKey);
		
		String  movieDbUrl = "https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey;
		System.out.println("movieDbUrl: "+movieDbUrl);
		MovieSummary  movieSummary =  restTemplate.getForObject(movieDbUrl,MovieSummary.class);
		System.out.println(movieSummary.toString());
		return new Movie(movieId, movieSummary.getTitle(),movieSummary.getOverview());
		
		
	}

}
