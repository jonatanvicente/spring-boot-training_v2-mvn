package com.training.springbootinitial.controller;

import com.training.springbootinitial.service.CountriesTestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/springboottraining/api/v1/initial")
public class InitialController {

    private static final Logger log = LoggerFactory.getLogger(InitialController.class);
    @Autowired
    CountriesTestService service;


    @GetMapping(value="/test")
    @ApiOperation("Get test")
    @ApiResponse(code = 200, message = "OK")
    public String test() {
        log.info("** Saludos desde el logger **");
        return "Hello from Initial Controller!!!";
    }

    @GetMapping("/test-reactive")
    @ApiOperation("Get Europe Countries")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Mono<?> getEuropeCountries(){
        return service.getEuropeCountries();
    }
}
