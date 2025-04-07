package com.training.springboottravelagency.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class PlaneTicket extends Ticket{

    private int numberOfSeats;
    private String brand;
    private String type;

    public PlaneTicket() {
        super.setTicketType("Plane Ticket");
    }
}
