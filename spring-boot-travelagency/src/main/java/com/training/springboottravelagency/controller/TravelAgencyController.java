package com.training.springboottravelagency.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/springboottraining/api/v1/travelagency")
public class TravelAgencyController {

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public String getPing(){
        return "Welcome to our Travel Agency  !!!";
    }

}
