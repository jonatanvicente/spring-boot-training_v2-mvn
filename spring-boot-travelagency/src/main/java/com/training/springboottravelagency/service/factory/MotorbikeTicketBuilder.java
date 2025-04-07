package com.training.springboottravelagency.service.factory;

import com.training.springboottravelagency.dto.output.BusTicket;
import com.training.springboottravelagency.dto.output.MotorbikeTicket;
import com.training.springboottravelagency.dto.output.Ticket;
import org.springframework.stereotype.Component;

@Component
public class MotorbikeTicketBuilder implements ITicketBuilder {

    @Override
    public Ticket buildTicket() {
        Ticket ticket = new MotorbikeTicket();
        ((MotorbikeTicket)ticket).setPlate("9967-JJW");
        return ticket;
    }
}
