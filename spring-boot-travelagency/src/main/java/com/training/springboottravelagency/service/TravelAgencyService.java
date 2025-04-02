package com.training.springboottravelagency.service;

import com.training.springboottravelagency.dto.in.Customer;
import com.training.springboottravelagency.dto.out.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TravelAgencyService implements ITravelAgencyService{

    @Autowired
    private Ticket ticket;

    @Override
    public Mono<Ticket> getTicket() {
        ticket.setTicketType("Generic Ticket");
        return Mono.just(ticket);
    }
}
