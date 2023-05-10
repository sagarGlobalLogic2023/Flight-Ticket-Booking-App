package com.flightbooking.flightticketbookingapp.controller;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.User;
import com.flightbooking.flightticketbookingapp.payload.BookFlightPayload;
import com.flightbooking.flightticketbookingapp.payload.UpdateProfilePayload;
import com.flightbooking.flightticketbookingapp.payload.UpdateUserPayload;
import com.flightbooking.flightticketbookingapp.service.FlightService;
import com.flightbooking.flightticketbookingapp.service.BookingService;
import com.flightbooking.flightticketbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @GetMapping("/search-flights")
    public ResponseEntity<List<Flight>> searchFlights(String source, String destination){
        List<Flight> flights= flightService.SearchFlights(source, destination);
        return ResponseEntity.status(HttpStatus.CREATED).body(flights);
    }

    @GetMapping("/show-bookings")
    public ResponseEntity<List<Booking>> showBookings(User userId){
//        List<Booking> bookings= bookingService.showBookings(userId);

        List<Booking> bookings = bookingService.showBookings(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookings);
    }

    @GetMapping("/get-booking")
    public ResponseEntity<Flight> getBooking(User userId, Flight flightId){
        Booking booking= bookingService.getBook(userId,flightId);
       Flight flight= booking.getFlight();
       return ResponseEntity.status(HttpStatus.CREATED).body((flight));
    }

    @PostMapping("/book-flight")
    public ResponseEntity<String> bookFlight(@RequestBody BookFlightPayload bookFlightPayload) {
        bookingService.bookFlight(bookFlightPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking Done");
    }

    @PutMapping("update-user-profile")
    public ResponseEntity<String> updateUser(@RequestBody UpdateProfilePayload updateProfilePayload){
        User user= userService.updateProfile(updateProfilePayload);
        if(user!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Profile updated successfully");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Error Updating Profile");
    }

    @PutMapping("cancel-booking")
    public ResponseEntity<String> cancelBooking(User userId, Flight flightId){
        bookingService.cancelBooking(userId,flightId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Booked Flight Cancelled Successfully");
    }



}