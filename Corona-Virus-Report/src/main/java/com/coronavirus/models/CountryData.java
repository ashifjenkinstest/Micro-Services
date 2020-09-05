package com.coronavirus.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class CountryData {
	
	@Id
	private Integer id;
	private String country;
	private String state;
	private String totalCases;
	private String latestCases;

}
