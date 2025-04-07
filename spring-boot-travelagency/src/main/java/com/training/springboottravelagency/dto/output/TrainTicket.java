package com.training.springboottravelagency.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class TrainTicket extends Ticket {

    private int numberOfSeats;
    private int stops;
    private String type;

    public TrainTicket() {
        super.setTicketType("Bus Ticket");
    }

}
