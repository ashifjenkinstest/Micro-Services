package com.coronavirus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.coronavirus.models.CVACountries;
//@Repository
public interface CVACountriesReposirtory extends JpaRepository<CVACountries, String>{

}
