package com.moviecatalogservice.resources;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviecatalogservice.models.CatalogItem;
import com.moviecatalogservice.models.Movie;
import com.moviecatalogservice.models.MovieRating;
import com.moviecatalogservice.models.UserRating;
import com.moviecatalogservice.services.CatalogItemService;
import com.moviecatalogservice.services.UserRatingService;
import com.moviecatalogservice.utils.RandomUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private WebClient.Builder builder;
	
	@Autowired
	private UserRatingService userRatingService;
	
	@Autowired
	private CatalogItemService catalogItemService;
	
	@Autowired
	private RandomUtil  rand;
	
	
	private List<MovieRating> movieRatings;
	
	@Value("${app.name}")
	private String appName;
	@Value("${app.desc}")
	private String appDescription;
	
	@Value("${app.title: Default Catalog Title, when property value not present in properties file. }")
	private String appTitle;
	
	@Value("${app.version}")
	private List<String> appVersion;
	
	
	@Value("#{${app.version.release}}")
	private Map<String,Integer> appVersionRelease;
	
	
	@RequestMapping("/{userId}")
	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId")String userId){
		
		
		///
		
		System.out.println("Application name: " + appName);
		System.out.println("Application description: " + appDescription);
		System.out.println("Application title :"+ appTitle);
		System.out.println("Application version :"+ appVersion.get(appVersion.size()-1));
		System.out.println("Application version and release: "+ appVersionRelease);
		
		
		///
		
		
		
		movieRatings = new ArrayList<MovieRating>();
		for(int i = 0;i<rand.getRandomRating(10)+1;i++) {
			movieRatings.add(new MovieRating(String.valueOf(rand.getRandomRating(200)) , rand.getRandomRating(10)));
		}
		/*
		movieRatings = Arrays.asList(
				new MovieRating(String.valueOf(rand.getRandomRating(200)) , rand.getRandomRating(10)),
				new MovieRating(String.valueOf(rand.getRandomRating(200)) , rand.getRandomRating(10))
				); 
		*/
		return movieRatings.stream().map(rating ->{
			Movie movie =   restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(Integer.parseInt(movie.getMovieId()),movie.getName(), movie.getMovieOverview(), rating.getRating())	;	
		}
		).collect(Collectors.toList());
		
	}
	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId")String userId){
		
		List<CatalogItem> catalogItems= new ArrayList<CatalogItem>();
		for (MovieRating rating : movieRatings) {
			catalogItems.add(new CatalogItem(Integer.parseInt(rating.getMovieId()), "No movie", "", rating.getRating()));
		}
		return catalogItems;
	}
	
	
	@RequestMapping("/{userId}/v1")
	public List<CatalogItem> getCatalogv1(@PathVariable("userId")String userId){
		UserRating userRating = userRatingService.getUserRating(userId)  ;// ,getUserRating(userId);
		
		/*
		UserRating movieRatings = restTemplate.getForObject("http://movie-rating-service/rating/users/" + userId, UserRating.class);
		*/
		System.out.println("getCatalogv1 " + userRating);
		return userRating.getMovieRatings().stream().map(
				rating ->{return catalogItemService.getCatalogItem(rating);}
		).collect(Collectors.toList());
		/*
		return movieRatings.getMovieRatings().stream().map(rating ->{
			Movie movie = builder
					.build()
					.get()
					.uri("http://movie-info-service/movies/"+ rating.getMovieId())
					.retrieve()
					.bodyToMono(Movie.class)
					.block();//We are blocking the execution till the mono  is finished.	
			return new CatalogItem(movie.getName(), movie.getMovieOverview(), rating.getRating())	;	
		}
		).collect(Collectors.toList());
		*/
		
	}
	
	
		
	
	
}
