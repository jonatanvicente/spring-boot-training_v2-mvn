package com.training.springboottravelagency.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.stereotype.Component;


@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class BusTicket extends Ticket {

    private int numberOfSeats;
    private int stops;
    private String plate;

    public BusTicket() {
        super.setTicketType("Bus Ticket");
    }

}
