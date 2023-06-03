package com.flightbooking.flightticketbookingapp.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class changeFlightStatusPayload {
    @NotNull
    @NotEmpty
    @NotBlank
    private String status;
    @NotNull
    private Long flightId;
}
