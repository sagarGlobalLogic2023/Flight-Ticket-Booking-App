package com.flightbooking.flightticketbookingapp.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightPayload {
    @NotNull
    private String source;
    private String destination;
    private Integer availableSeats;
    private String departure;
    private String arrival;
    private Long planeId;
    private Integer fare;


}
