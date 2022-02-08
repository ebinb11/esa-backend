package com.employee.employeebackend.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.employee.employeebackend.dto.CountriesDTO;
import com.employee.employeebackend.service.CovidService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service
public class CovidServiceImpl implements CovidService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public CountriesDTO[] getCountries() {
		CountriesDTO[] countriesGet = null;
		List<CountriesDTO> countriesDTOList = new ArrayList<>();
		try {
			countriesGet = restTemplate.getForObject("https://api.covid19api.com/countries", CountriesDTO[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (CountriesDTO countriesGeta : countriesGet) {
			if(countriesGeta.getCountry().contains("India")) {
				countriesDTOList.add(countriesGeta);
			}
		}
		
		//JSon Filter
		SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
		simpleFilterProvider.addFilter("countriesFilter", SimpleBeanPropertyFilter.filterOutAllExcept("country"));
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setFilterProvider(simpleFilterProvider);
		
		
		return countriesGet;
	}
}
