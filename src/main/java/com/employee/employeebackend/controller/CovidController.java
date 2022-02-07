package com.employee.employeebackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employeebackend.dto.CountriesDTO;
import com.employee.employeebackend.service.CovidService;
import com.employee.employeebackend.utils.AppResponse;

@RestController
public class CovidController {
	
	@Autowired
	CovidService covidService;

	@GetMapping("getCountries")
	public AppResponse<CountriesDTO[]> getCountries() {
		CountriesDTO[] response = covidService.getCountries();
		if (response != null) {
			return AppResponse.<CountriesDTO[]> builder()
					.data(response)
					.message("Countries are fetched successfuly!")
					.success(true)
					.build();
			}
		return AppResponse.<CountriesDTO[]> builder()
				.message("Something went wrong!, please try again later")
				.success(false)
				.build();
	}
	
}
