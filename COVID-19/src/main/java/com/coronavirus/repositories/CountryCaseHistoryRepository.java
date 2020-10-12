package com.coronavirus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coronavirus.models.CountryCaseHistory;

public interface CountryCaseHistoryRepository extends JpaRepository<CountryCaseHistory, String>{

	List<CountryCaseHistory> findAllByCountryOrderByDateOfCaseDesc(String country);

}
