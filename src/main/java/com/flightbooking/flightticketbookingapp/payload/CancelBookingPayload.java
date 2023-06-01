package com.flightbooking.flightticketbookingapp.payload;

import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelBookingPayload {
    @NotNull
    private User user;
    @NotNull
    private Flight flight;
}
