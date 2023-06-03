package com.flightbooking.flightticketbookingapp.payload;

import com.flightbooking.flightticketbookingapp.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class showBookingsPayload {
    @NotNull
   private User user;
}
