package com.flightbooking.flightticketbookingapp.request;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.entity.Plane;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class FlightRequest {
        private Long flightId;
        private String source;
        private String destination;
        private Integer availableSeats;
        private Integer fare;
        private LocalDateTime departure;
        private LocalDateTime arrival;
        private Long duration;
        private Plane planeId;
        private String status;
    }

