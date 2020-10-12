package com.coronavirus.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@IdClass(CountryCaseHistoryId.class)
public class CountryCaseHistory {

	
	/*
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	private Long id;
	 */
	
	@Id
	@JsonProperty("Country")
	private String country;

	
	@JsonProperty("CountryCode")
	private String countryCode;
	
	@JsonProperty("Province")
	private String province;
	
	@JsonProperty("City")
	private String city;
	
	@JsonProperty("CityCode")
	private String cityCode;
	
	@JsonProperty("Lat")
	private String lat;
	
	@JsonProperty("Lon")
	private String lon;
	
	@JsonProperty("Confirmed")
	private long confirmed;
	
	@JsonProperty("Recovered")
	private long recovered;
	
	@JsonProperty("Active")
	private long active;
	
	@Id
	@JsonProperty("Date")
	private Date dateOfCase;
	
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, updatable = false)
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	public CountryCaseHistory(String country, String countryCode, String province, String city, String cityCode,
			String lat, String lon, long confirmed, long recovered, long active, Date dateOfCase) {
		super();
		this.country = country;
		this.countryCode = countryCode;
		this.province = province;
		this.city = city;
		this.cityCode = cityCode;
		this.lat = lat;
		this.lon = lon;
		this.confirmed = confirmed;
		this.recovered = recovered;
		this.active = active;
		this.dateOfCase = dateOfCase;
	}

	
		


	
	
}
