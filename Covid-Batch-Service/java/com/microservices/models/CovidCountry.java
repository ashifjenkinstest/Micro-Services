package com.microservices.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity(name = "COVID_COUNTRY_BATCH")
@IdClass(CovidCountry.class)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class CovidCountry implements Serializable {
	
	//@Id
	
	/*
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COVID_COUNTRY_SEQ")
    @SequenceGenerator(sequenceName = "COVID_COUNTRY_SEQ", allocationSize = 1, name = "COVID_COUNTRY_SEQ")
    Long id;
	*/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COUNTRY")
	private String countryRegion;
	
	@Id
	@Column(name = "PROVINCE",nullable = true )
	private String provinceState;
	
	@Column(name = "LATITUDE")
	private String lat;
	
	@Column(name = "LONGITUDE")
	private String lon;
	
	/*
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, updatable = false)
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	*/


}
