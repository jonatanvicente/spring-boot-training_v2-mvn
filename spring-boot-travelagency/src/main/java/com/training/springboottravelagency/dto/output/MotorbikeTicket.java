package com.training.springboottravelagency.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class MotorbikeTicket extends Ticket {

    private String plate;

    public MotorbikeTicket() {
        super.setTicketType("Motorbike Ticket");
    }

}
