package com.coronavirus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coronavirus.models.AllCountriesData;

public interface AllCountriesDataRepository  extends JpaRepository<AllCountriesData, Integer>{

}
