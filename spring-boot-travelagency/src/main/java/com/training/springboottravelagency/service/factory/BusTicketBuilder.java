package com.training.springboottravelagency.service.factory;

import com.training.springboottravelagency.dto.output.BusTicket;
import com.training.springboottravelagency.dto.output.Ticket;
import org.springframework.stereotype.Component;

@Component
public class BusTicketBuilder implements ITicketBuilder {

    @Override
    public Ticket buildTicket() {
        Ticket ticket = new BusTicket();
        ((BusTicket)ticket).setPlate("9099-MMK");
        ((BusTicket)ticket).setNumberOfSeats(50);
        ((BusTicket)ticket).setStops(5);
        return ticket;
    }
}
