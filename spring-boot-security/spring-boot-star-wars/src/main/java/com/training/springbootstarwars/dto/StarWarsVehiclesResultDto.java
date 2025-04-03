package com.training.springbootstarwars.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class StarWarsVehiclesResultDto {
	
    private int count;
    private String next;
    private String previous;
    
    private StarWarsVehicleDto[] results;

}
