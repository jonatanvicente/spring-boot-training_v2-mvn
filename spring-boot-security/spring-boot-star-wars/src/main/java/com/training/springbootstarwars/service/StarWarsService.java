package com.training.springbootstarwars.service;

import com.training.springbootstarwars.config.PropertiesConfig;
import com.training.springbootstarwars.dto.StarWarsVehiclesResultDto;
import com.training.springbootstarwars.proxy.HttpProxy;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Service
public class StarWarsService {

    @Autowired
    PropertiesConfig config;

    @Autowired
    HttpProxy httpProxy;
    @CircuitBreaker(name = "circuitBreaker", fallbackMethod = "logInternalErrorReturnDefaultPage")
    public Mono<StarWarsVehiclesResultDto> getStarWarsVehicles() throws MalformedURLException{
           return httpProxy.getRequestData(new URL(config.getDs_test()), StarWarsVehiclesResultDto.class);
    }
    private Mono<StarWarsVehiclesResultDto> logInternalErrorReturnDefaultPage(Throwable exception) {
        log.error("Error: "+exception.getMessage());
        return Mono.just(new StarWarsVehiclesResultDto());
    }

}
