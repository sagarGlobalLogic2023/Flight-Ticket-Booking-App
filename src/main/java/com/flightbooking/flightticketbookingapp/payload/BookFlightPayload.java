package com.flightbooking.flightticketbookingapp.payload;

import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFlightPayload {

    private Long userId;
    private Long flightId;
    private LocalDateTime createdAt;
    private Integer seatNumber;

}
