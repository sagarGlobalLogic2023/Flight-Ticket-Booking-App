package com.flightbooking.flightticketbookingapp.payload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class SearchFlightPayload {
    @NotNull
    @NotEmpty
    @NotBlank
    private String source;
    @NotEmpty
    @NotBlank
    @NotNull
    private String destination;
}
