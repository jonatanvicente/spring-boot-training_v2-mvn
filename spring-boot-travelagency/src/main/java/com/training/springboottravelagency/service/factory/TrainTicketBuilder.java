package com.training.springboottravelagency.service.factory;

import com.training.springboottravelagency.dto.output.BusTicket;
import com.training.springboottravelagency.dto.output.PlaneTicket;
import com.training.springboottravelagency.dto.output.Ticket;
import com.training.springboottravelagency.dto.output.TrainTicket;
import org.springframework.stereotype.Component;

@Component
public class TrainTicketBuilder  implements ITicketBuilder {

    @Override
    public Ticket buildTicket() {
        Ticket ticket = new TrainTicket();
        ((TrainTicket) ticket).setNumberOfSeats(400);
        ((TrainTicket) ticket).setType("High Velocity");
        ((TrainTicket) ticket).setStops(2);
        return ticket;
    }

}