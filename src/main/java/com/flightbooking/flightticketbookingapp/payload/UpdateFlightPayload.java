package com.flightbooking.flightticketbookingapp.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateFlightPayload {

    @NotNull
    private Long flightId;
    @NotNull
    @NotEmpty
    @NotBlank
    private String source;
    @NotNull
    @NotEmpty
    @NotBlank
    private String destination;
    @NotNull
    private Integer availableSeats;
    @NotNull
    @NotEmpty
    @NotBlank
    private String departure;
    @NotNull
    @NotEmpty
    @NotBlank
    private String arrival;
    @NotNull
    private Long planeId;
    @NotNull
    private Integer fare;
    @NotNull
    @NotEmpty
    @NotBlank
    private String status;
}
