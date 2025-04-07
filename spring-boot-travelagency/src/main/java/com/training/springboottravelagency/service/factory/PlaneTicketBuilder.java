package com.training.springboottravelagency.service.factory;

import com.training.springboottravelagency.dto.output.BusTicket;
import com.training.springboottravelagency.dto.output.MotorbikeTicket;
import com.training.springboottravelagency.dto.output.PlaneTicket;
import com.training.springboottravelagency.dto.output.Ticket;
import org.springframework.stereotype.Component;

@Component
public class PlaneTicketBuilder implements ITicketBuilder {

        @Override
        public Ticket buildTicket() {
            Ticket ticket = new PlaneTicket();
            ((PlaneTicket)ticket).setBrand("Airbus");
            ((PlaneTicket)ticket).setType("Commercial");
            ((PlaneTicket)ticket).setNumberOfSeats(500);
            return ticket;
        }

}
