package com.training.springboottravelagency.service;

import com.training.springboottravelagency.dto.out.Ticket;
import reactor.core.publisher.Mono;

public interface ITravelAgencyService {

    Mono<Ticket> getTicket();
}
