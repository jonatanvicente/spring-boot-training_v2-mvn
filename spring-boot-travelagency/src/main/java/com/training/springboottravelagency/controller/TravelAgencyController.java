package com.training.springboottravelagency.controller;

import com.training.springboottravelagency.dto.in.Customer;
import com.training.springboottravelagency.dto.out.Ticket;
import com.training.springboottravelagency.service.ITravelAgencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/springboottraining/api/v1/travelagency")
public class TravelAgencyController {


    @Autowired
    private ITravelAgencyService travelAgencyService;

    @Operation(summary = "Ping our Travel Agency", tags = "Ping")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
    })
    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public String getPing(){
        return "Welcome to our Travel Agency  !!!";
    }


    @Operation(summary = "Get Ticket from our Travel Agency", tags = "Get Ticket")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
    })
    @GetMapping("/ticket")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Ticket> getTicket(){
        return travelAgencyService.getTicket();
    }

}
