package com.coronavirus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coronavirus.models.Country;

public interface CountryRepository  extends JpaRepository<Country, String>{
	
	public List<Country> findAllByOrderByCountryAsc();
	public Country findByIso2(String iso2);

}
