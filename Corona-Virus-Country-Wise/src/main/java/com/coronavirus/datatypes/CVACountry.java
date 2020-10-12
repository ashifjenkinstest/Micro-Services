package com.coronavirus.datatypes;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CVACountry {

	private String Country;
	private String Slug;
	private String ISO2;
	
	private Date createDate;
	
	private Date modifyDate;
	
}
