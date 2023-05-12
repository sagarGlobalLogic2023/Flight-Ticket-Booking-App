package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.entity.BookingId;
import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {




    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void showBookings() {

        List<Booking> bookings = new ArrayList<>();




    }

    @Test
    void getBook() {
    }

    @Test
    void bookFlight() {

        BookingId bookingId;
        User userId;
        Flight flightId;
        LocalDateTime createdAt;

        //Booking booking = new Booking(bookingId, userId, flightId, createdAt, 32, "Confirmed");

    }

    @Test
    void cancelBooking() {
    }
}