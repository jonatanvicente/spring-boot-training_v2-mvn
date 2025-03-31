package com.training.springbootinitial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountriesResponseDto {

    @JsonProperty("count")
    private int count;

    @JsonProperty("elements")
    private CountriesDto[] elements;
}
