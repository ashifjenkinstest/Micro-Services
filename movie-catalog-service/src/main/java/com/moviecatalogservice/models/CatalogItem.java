package com.moviecatalogservice.models;

public class CatalogItem {
	
	
	private int movieId;
	private String name;
	private String desc;
	private int rating;
	
	
	
	

	public CatalogItem(int movieId, String name, String desc, int rating) {
		super();
		this.movieId = movieId;
		this.name = name;
		this.desc = desc;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	@Override
	public String toString() {
		return "CatalogItem [movieId=" + movieId + ", name=" + name + ", desc=" + desc + ", rating=" + rating + "]";
	}
	
	
	
	
	

}
