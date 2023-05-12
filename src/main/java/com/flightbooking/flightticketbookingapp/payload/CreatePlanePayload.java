package com.flightbooking.flightticketbookingapp.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreatePlanePayload {
    @NotNull
    private Integer capacity;
    @NotBlank
    @NotEmpty
    @NotNull
    private String airline;
    @NotNull
    @NotBlank
    @NotEmpty
    private String status;
}
