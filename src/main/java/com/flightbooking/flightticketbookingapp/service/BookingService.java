package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Booking;

import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.entity.User;
import com.flightbooking.flightticketbookingapp.payload.BookFlightPayload;
import com.flightbooking.flightticketbookingapp.repository.BookingRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

    public List<Booking> showBookings(User userId) {

//        List<Booking> bookings = bookingRepo.getAllByUserid(userId);
        List<Booking> bookings = bookingRepo.findAllByUser(userId);

        return bookings;
    }
    public Booking getBook(User userId, Flight flightId){
       return bookingRepo.findByUserAndFlight(userId,flightId);
    }

    public void bookFlight(BookFlightPayload bookFlightPayload) {

//        Optional<Booking> bookingId = bookingRepo.findById(bookFlightPayload.)

        Booking booking = new Booking();

        booking.setFlight(flightService.getFlight(bookFlightPayload.getFlightId()));
        booking.setUser(userService.getUser(bookFlightPayload.getUserId()));
        booking.setCreatedAt(bookFlightPayload.getCreatedAt());
        booking.setSeatNumber(bookFlightPayload.getSeatNumber());

        bookingRepo.save(booking);

    }

}
