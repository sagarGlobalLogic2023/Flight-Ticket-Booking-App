package com.flightbooking.flightticketbookingapp.payload;

import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookFlightPayload {

    private Long userId;
    private Long flightId;
    private LocalDateTime createdAt;
    private Integer seatNumber;


}
