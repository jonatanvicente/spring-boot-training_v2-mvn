package com.training.springboottravelagency.service;

import com.training.springboottravelagency.dto.input.Customer;
import com.training.springboottravelagency.dto.output.*;
import com.training.springboottravelagency.service.factory.TicketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TravelAgencyService implements ITravelAgencyService{

    @Autowired
    private TicketFactory ticketFactory;

    @Override
    public Mono<Ticket> getTicket(Customer c) {
        return Mono.just(ticketFactory.build(c));
    }
}
