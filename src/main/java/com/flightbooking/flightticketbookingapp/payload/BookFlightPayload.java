package com.flightbooking.flightticketbookingapp.payload;

import com.flightbooking.flightticketbookingapp.entity.Flight;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFlightPayload {

    @NotNull
    private Long userId;
    @NotNull
    private Long flightId;

//    @NotNull
//    @NotEmpty
//    @NotBlank
//    private String createdAt;
    @NotNull
    private Integer seatNumber;

}
