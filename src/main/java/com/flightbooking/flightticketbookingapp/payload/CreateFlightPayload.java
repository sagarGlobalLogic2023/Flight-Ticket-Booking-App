package com.flightbooking.flightticketbookingapp.payload;

import lombok.Data;

@Data
public class CreateFlightPayload {
    private String source;
    private String destination;
    private Integer availableSeats;
    private String departure;
    private String arrival;
    private Long planeId;
    private Integer fare;

}
