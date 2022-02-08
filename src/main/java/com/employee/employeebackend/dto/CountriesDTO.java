package com.employee.employeebackend.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"slug"})
@JsonFilter("countriesFilter")
public class CountriesDTO {
	
	@JsonProperty(value = "Country")
	private String country;
	@JsonProperty(value = "Slug")
	private String slug;
	@JsonProperty(value = "ISO2")
	private String iso2;

}
