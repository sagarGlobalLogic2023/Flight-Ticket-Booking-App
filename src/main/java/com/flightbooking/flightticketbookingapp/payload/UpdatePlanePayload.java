package com.flightbooking.flightticketbookingapp.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePlanePayload {

    @NotNull
    private Long planeId;
    @NotNull
    private Integer capacity;
    @NotNull
    @NotEmpty
    @NotBlank
    private String airline;
    @NotNull
    @NotEmpty
    @NotBlank
    private String status;

}
