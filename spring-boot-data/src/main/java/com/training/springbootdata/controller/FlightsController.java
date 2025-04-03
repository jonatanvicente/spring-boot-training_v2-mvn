package com.training.springbootdata.controller;


import com.training.springbootdata.entity.Airport;
import com.training.springbootdata.service.AirportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/springboottraining/api/v1/data")
public class FlightsController {

    @Autowired
    private AirportServiceImpl service;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Searching flights.....");
    }


    @RequestMapping(value = "/airport/all", method = RequestMethod.GET)
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = service.getAllAirports();
        return airports == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(airports);
    }

    @RequestMapping(value = "/airport/departures/{airport}", method = RequestMethod.GET)
    public ResponseEntity<Airport> getOriginInfo(@PathVariable(name="airport") String airport) {

        Optional<Airport> result = service.findAirport(airport.toUpperCase());

        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/airport/count")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> getAirportCount(){
        return service.airportCount();
    }

}
