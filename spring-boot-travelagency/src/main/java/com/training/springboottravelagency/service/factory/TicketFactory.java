package com.training.springboottravelagency.service.factory;

import com.training.springboottravelagency.bo.config.Range;
import com.training.springboottravelagency.dto.input.Customer;
import com.training.springboottravelagency.dto.output.Ticket;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class TicketFactory {

    private static final Map<Range, ITicketBuilder> builders = new HashMap<>();

    static {
        builders.put(new Range(0, 12), new BusTicketBuilder());
        builders.put(new Range(13, 18), new MotorbikeTicketBuilder());
        builders.put(new Range(19, 65), new PlaneTicketBuilder());
        builders.put(new Range(66, 100), new TrainTicketBuilder());
    }

    public Ticket build(Customer c) {
        return getBuilder(c.getAge()).buildTicket();
    }

    private ITicketBuilder getBuilder(int value) {
        for (Map.Entry<Range, ITicketBuilder> entry : builders.entrySet()) {
            if (entry.getKey().contains(value)) {
                return entry.getValue();
            }
        }
        return null;
    }

}
