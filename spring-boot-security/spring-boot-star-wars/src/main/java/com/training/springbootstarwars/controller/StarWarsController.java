package com.training.springbootstarwars.controller;

import com.training.springbootstarwars.service.StarWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;

@RestController
@RequestMapping(value = "/springboottraining/api/v1/starwars")
public class StarWarsController {

    @Autowired
    private StarWarsService service;
    @GetMapping("/vehicles")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getStarWarsVehicles() throws MalformedURLException {
        return service.getStarWarsVehicles();
    }

    @Secured("SUPERUSER") //Forbidden for user jvicente@gmail.com
    @GetMapping("/vehiclesWithSecurity")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getStarWarsVehiclesWithSecurity() throws MalformedURLException {
        return service.getStarWarsVehicles();
    }
}
