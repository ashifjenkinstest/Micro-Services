/**
 * 
 */
package com.coronavirus.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ASHIF
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Country {
	
	
	
	
	@Id
	@JsonProperty("Country")
	private String country;
	@JsonProperty("Slug")
	private String slug;
	@JsonProperty("ISO2")
	private String iso2;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, updatable = false)
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	public Country(String country, String slug, String iso2) {
		super();
		this.country = country;
		this.slug = slug;
		this.iso2 = iso2;
	}

	
}
