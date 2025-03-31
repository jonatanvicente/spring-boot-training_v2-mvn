package com.training.springbootinitial.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CountriesDto {
        private int idCountry;
        private String countryName;

        public CountriesDto(int idCountry, String countryName) {
            this.idCountry = idCountry;
            this.countryName = countryName;
        }

        @JsonGetter("idCountry")
        public int getIdCountry() {
            return idCountry;
        }

        @JsonSetter("idCountry")
        public void setIdCountry(int idCountry) {
            this.idCountry = idCountry;
        }

        @JsonGetter("countryName")
        public String getCountryName() {
            return countryName;
        }

        @JsonSetter("countryName")
        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }
    }
