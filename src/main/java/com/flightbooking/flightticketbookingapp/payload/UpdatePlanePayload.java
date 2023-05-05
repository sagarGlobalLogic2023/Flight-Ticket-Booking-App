package com.flightbooking.flightticketbookingapp.payload;

import lombok.Data;

@Data
public class UpdatePlanePayload {

    private Long planeId;
    private Integer capacity;
    private String airline;
    private String status;

}
