package com.training.springboottravelagency.service;

import com.training.springboottravelagency.dto.input.Customer;
import com.training.springboottravelagency.dto.output.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TravelAgencyService implements ITravelAgencyService{

    private Ticket ticket;

    @Override
    public Mono<Ticket> getTicket(Customer c) {

/*        ticket = new BusTicket();
        ((BusTicket)ticket).setPlate("9099-MMK");
        ((BusTicket)ticket).setNumberOfSeats(50);
        ((BusTicket)ticket).setStops(5);

        ticket = new MotorbikeTicket();
        ((MotorbikeTicket)ticket).setPlate("9099-MMK");

        ticket = new PlaneTicket();
        ((PlaneTicket)ticket).setBrand("Boeing");
        ((PlaneTicket)ticket).setType("Passenger");
        ((PlaneTicket)ticket).setNumberOfSeats(200);*/

        ticket = new TrainTicket();
        ((TrainTicket)ticket).setStops(3);
        ((TrainTicket)ticket).setType("Passenger");
        ((TrainTicket)ticket).setNumberOfSeats(200);


        return Mono.just(ticket);
    }
}
