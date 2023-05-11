package com.flightbooking.flightticketbookingapp.mockEntity;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.request.FlightRequest;
import com.flightbooking.flightticketbookingapp.response.FlightResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MockFlightObject {

    // Requested (Expected)
    public static FlightRequest getFlightRequest() {

        // mock data
        Plane plane = new Plane();
        plane.setPlaneId(1L);

        // mock data
        List<Booking> bookings = new ArrayList<>();

        FlightRequest flightRequest = new FlightRequest();

        flightRequest.setFlightId(1L);
        flightRequest.setSource("Delhi");
        flightRequest.setDestination("Islamabad");
        flightRequest.setDeparture(LocalDateTime.parse("2017-01-13T17:07:42.411"));
        flightRequest.setArrival(LocalDateTime.parse("2017-01-13T17:09:42.411"));
        flightRequest.setDuration(2L);
        flightRequest.setFare(5000);
        flightRequest.setAvailableSeats(240);
        flightRequest.setPlaneId(plane);
        flightRequest.setStatus("Confirmed");
       // flightRequest.setBookings(bookings);

        return flightRequest;

    }

    // (Actual)
    public static Flight getFlight() {

        // mock data
        Plane plane = new Plane();
        plane.setPlaneId(1L);

        // mock data
        List<Booking> bookings = new ArrayList<>();

        Flight flight = new Flight();

        flight.setFlightId(1L);
        flight.setSource("Delhi");
        flight.setDestination("Islamabad");
        flight.setDeparture(LocalDateTime.parse("2017-01-13T17:07:42.411"));
        flight.setArrival(LocalDateTime.parse("2017-01-13T17:09:42.411"));
        flight.setDuration(2L);
        flight.setFare(5000);
        flight.setAvailableSeats(240);
        flight.setPlane(plane);
        flight.setStatus("Confirmed");
        flight.setBookings(bookings);

        return flight;
    }

    // Response (Actual)
    public static FlightResponse getFlightResponse() {

        FlightResponse flightResponse = new FlightResponse();

        flightResponse.setFlightId(getFlight().getFlightId());
        flightResponse.setSource(getFlight().getSource());
        flightResponse.setDestination(getFlight().getDestination());
        flightResponse.setDeparture(getFlight().getDeparture());
        flightResponse.setArrival(getFlight().getArrival());
        flightResponse.setDuration(getFlight().getDuration());
        flightResponse.setFare(getFlight().getFare());
        flightResponse.setAvailableSeats(getFlight().getAvailableSeats());
        flightResponse.setPlaneId(getFlight().getPlane());
        flightResponse.setStatus(getFlight().getStatus());
       // flightResponse.setBookings(getFlight().getBookings());

        return flightResponse;
    }
}

