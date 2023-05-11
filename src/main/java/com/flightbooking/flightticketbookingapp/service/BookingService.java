package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.*;

import com.flightbooking.flightticketbookingapp.payload.BookFlightPayload;
import com.flightbooking.flightticketbookingapp.repository.BookingRepo;

import com.flightbooking.flightticketbookingapp.repository.FlightRepo;
import com.flightbooking.flightticketbookingapp.repository.UserRepo;
import com.flightbooking.flightticketbookingapp.user.User;
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
    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private UserRepo userRepo;

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
        BookingId bookingId = new BookingId();
        bookingId.setCreatedAt(LocalDateTime.now());
        bookingId.setFlightId(bookFlightPayload.getFlightId());
        bookingId.setUserId(bookFlightPayload.getUserId());
        booking.setId(bookingId);
        booking.setFlight(flightService.getFlight(bookFlightPayload.getFlightId()));
        booking.setUser(userService.getUser(bookFlightPayload.getUserId()));
        booking.setCreatedAt(LocalDateTime.now());
        booking.setSeatNumber(bookFlightPayload.getSeatNumber());
        booking.setStatus("Confirmed");


        bookingRepo.save(booking);

    }
    public void cancelBooking(User userId, Flight flightId){
        Booking booking = new Booking();
        Booking booking1= bookingRepo.findByUserAndFlight(userId,flightId);
        booking.setId(booking1.getId());
        booking.setStatus("Cancelled");
        booking.setUser(booking1.getUser());
        booking.setFlight(booking1.getFlight());
        booking.setCreatedAt(booking1.getCreatedAt());
        booking.setSeatNumber(booking1.getSeatNumber());
        bookingRepo.save(booking);
    }

}
