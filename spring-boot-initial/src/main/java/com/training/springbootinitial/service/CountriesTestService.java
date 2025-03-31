package com.training.springbootinitial.service;

import com.training.springbootinitial.config.PropertiesConfig;
import com.training.springbootinitial.dto.CountriesDto;
import com.training.springbootinitial.dto.CountriesResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CountriesTestService {

    @Autowired
    private PropertiesConfig config;

    public Mono<CountriesResponseDto> getEuropeCountries(){
        String[] countriesConfig = config.getCountryNames();

        CountriesDto[] countries = new CountriesDto[countriesConfig.length];
        for(int i = 1; i <= countriesConfig.length; i++){
            countries[i-1] = new CountriesDto(i,countriesConfig[i-1]);
        }
        CountriesResponseDto response = new CountriesResponseDto(countries.length, countries);
        return Mono.just(response);

    }
}
