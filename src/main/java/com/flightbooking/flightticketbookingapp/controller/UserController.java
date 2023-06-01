package com.flightbooking.flightticketbookingapp.controller;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.payload.*;
import com.flightbooking.flightticketbookingapp.repository.FlightRepo;
import com.flightbooking.flightticketbookingapp.service.FlightService;
import com.flightbooking.flightticketbookingapp.service.BookingService;
import com.flightbooking.flightticketbookingapp.service.UserService;
import com.flightbooking.flightticketbookingapp.user.User;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/search-flights")
    public ResponseEntity<?> searchFlights(@RequestBody @Valid SearchFlightPayload searchFlightPayload){
        logger.info("Endpoint '/search-flights' called by a user with ROLE_USER");
        logger.debug("Search Flight Payload: {}", searchFlightPayload);
        List<Flight> flights= flightService.SearchFlights(searchFlightPayload.getSource(),searchFlightPayload.getDestination());
        if(flights.size()!=0) {
            logger.info("Flights found for the given search criteria");
            return ResponseEntity.status(HttpStatus.OK).body(flights);
        }
        else {
            logger.info("No flights found for the given search criteria");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Flights Found");
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/show-bookings")
    public ResponseEntity<?> showBookings( @RequestBody @Valid showBookingsPayload showBookingsPayload){
//        List<Booking> bookings= bookingService.showBookings(userId);
        logger.info("Endpoint '/show-bookings' called by a user with ROLE_USER");
        logger.debug("Show Bookings Payload: {}", showBookingsPayload);
        List<Booking> bookings = bookingService.showBookings(showBookingsPayload.getUser());
        if(bookings.size()!=0) {
            logger.info("Bookings found for the given user");
            return ResponseEntity.status(HttpStatus.OK).body(bookings);
        }
        else{
            logger.info("No bookings found for the given user");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking Not Found for this UserID");
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/get-booking")
    public ResponseEntity<?> getBooking(@RequestBody @Valid GetBookingPayload getBookingPayload ) {
        logger.info("Endpoint '/get-booking' called by a user with ROLE_USER");
        logger.debug("Get Booking Payload: {}", getBookingPayload);
        List<Booking> booking = bookingService.getBook(getBookingPayload.getUser(), getBookingPayload.getFlight());
        if (booking.size() != 0) {
            logger.info("Bookings found for the given user and flight");
            return ResponseEntity.status(HttpStatus.OK).body(booking);
        }
        else {
            logger.info("No bookings found for the given user and flight");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Bookings found");
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/book-flight")
    public ResponseEntity<String> bookFlight(@RequestBody @Valid BookFlightPayload bookFlightPayload) {
        logger.info("Endpoint '/book-flight' called by a user with ROLE_USER");
        logger.debug("Book Flight Payload: {}", bookFlightPayload);
        Optional<Flight> flight= flightRepo.findById(bookFlightPayload.getFlightId());
        if (flight.isPresent()) {
            if(flight.get().getAvailableSeats()!=0) {
                bookingService.bookFlight(bookFlightPayload);
                Flight flight1 = flight.get();
                flight1.setAvailableSeats(flight1.getAvailableSeats() - 1);
                flightRepo.save(flight1);
                logger.info("Booking successfully done");
                return ResponseEntity.status(HttpStatus.CREATED).body("Booking Done");
            }
            else {
                logger.info("Seats are full, no more bookings possible");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Seats Full , no more bookings possible!");
            }
        }
        else {
            logger.info("Flight not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found!");
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("update-user-profile")
    public ResponseEntity<String> updateUser(@RequestBody @Valid UpdateProfilePayload updateProfilePayload){
        logger.info("Endpoint '/update-user-profile' called by a user with ROLE_USER");
        logger.debug("Update Profile Payload: {}", updateProfilePayload);
        String response= userService.updateProfile(updateProfilePayload);
        if(response.equals("Profile updated successfully")) {
            logger.info("Profile updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body("Profile updated successfully");
        }
        else {
            logger.info("Error updating profile, user not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error Updating Profile, user not found");
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("cancel-booking")
    public ResponseEntity<String> cancelBooking(@RequestBody @Valid CancelBookingPayload cancelBookingPayload){
        logger.info("Endpoint '/cancel-booking' called by a user with ROLE_USER");
        logger.debug("Cancel Booking Payload: {}", cancelBookingPayload);
        String response=bookingService.cancelBooking(cancelBookingPayload.getUser(),cancelBookingPayload.getFlight());
        if(response.equals("Booked Flight Cancelled Successfully")) {
            logger.info("Booked flight cancelled successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            logger.info("Failed to cancel the booked flight");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}