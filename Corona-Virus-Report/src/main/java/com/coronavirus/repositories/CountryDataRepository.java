package com.coronavirus.repositories;

import org.springframework.data.repository.CrudRepository;

import com.coronavirus.models.CountryData;

public interface CountryDataRepository  extends CrudRepository<CountryData, Integer>{

}
