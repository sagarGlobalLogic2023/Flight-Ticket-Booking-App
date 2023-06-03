package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.entity.BookingId;
import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.payload.BookFlightPayload;
import com.flightbooking.flightticketbookingapp.repository.BookingRepo;
import com.flightbooking.flightticketbookingapp.repository.FlightRepo;
import com.flightbooking.flightticketbookingapp.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;
    @MockBean
    private BookingRepo bookingRepo;
    @MockBean
    private FlightService flightService;
    @MockBean
    private FlightRepo flightRepo;
    @MockBean
    private UserService userService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void showBookings() {
        User user= new User();
        user.setUserId(1L);
        User user1= new User();
        Flight flight= new Flight();
        Flight flight1= new Flight();
        List<Booking> bookings= new ArrayList<>();
        bookings.add(new Booking(new BookingId(1L,1L, LocalDateTime.parse("2017-01-13T17:09:42.411")),user,flight,LocalDateTime.parse("2017-01-13T17:09:42.411"),40,"Confirmed"));
        bookings.add(new Booking(new BookingId(2L,2L, LocalDateTime.parse("2017-01-13T17:09:42.411")),user1,flight1,LocalDateTime.parse("2017-01-13T17:09:42.411"),45,"Confirmed"));
        Mockito.when(bookingRepo.findAllByUser(user)).thenReturn(bookings);
        assertEquals(bookings,bookingService.showBookings(user));
        Mockito.verify(bookingRepo,Mockito.times(1)).findAllByUser(user);
    }

    @Test
    void getBook() {
        User user= new User();
        user.setUserId(1L);
        Flight flight= new Flight();
        flight.setFlightId(1L);
        List<Booking> bookings= new ArrayList<>();
        bookings.add(new Booking(new BookingId(1L,1L, LocalDateTime.parse("2017-01-13T17:09:42.411")),user,flight,LocalDateTime.parse("2017-01-13T17:09:42.411"),40,"Confirmed"));
        bookings.add(new Booking(new BookingId(1L,1L, LocalDateTime.parse("2020-01-22T20:10:42.411")),user,flight,LocalDateTime.parse("2020-01-22T20:10:42.411"),45,"Confirmed"));
        Mockito.when(bookingRepo.findAllByUserAndFlight(user,flight)).thenReturn(bookings);
        assertEquals(bookings,bookingService.getBook(user,flight));
        Mockito.verify(bookingRepo).findAllByUserAndFlight(Mockito.any(User.class),Mockito.any(Flight.class));
    }

    @Test
    void bookFlight() {
        BookFlightPayload bookFlightPayload= new BookFlightPayload();
        bookFlightPayload.setFlightId(1L);
        bookFlightPayload.setUserId(1L);
        bookFlightPayload.setSeatNumber(35);
        Flight flight= new Flight();
        flight.setFlightId(1L);
        User user= new User();
        user.setUserId(1L);
        Mockito.when(flightService.getFlight(bookFlightPayload.getFlightId())).thenReturn(flight);
        Mockito.when(userService.getUser(bookFlightPayload.getUserId())).thenReturn(user);
        bookingService.bookFlight(bookFlightPayload);
        Mockito.verify(bookingRepo,Mockito.times(1)).save(Mockito.any(Booking.class));
    }

    @Test
    void cancelBooking() {
        User user= new User();
        Flight flight= new Flight();
        flight.setFlightId(1L);
        flight.setAvailableSeats(50);
        Booking booking= new Booking(new BookingId(1L,1L, LocalDateTime.parse("2017-01-13T17:09:42.411")),user,flight,LocalDateTime.parse("2017-01-13T17:09:42.411"),40,"Confirmed");
        Mockito.when(bookingRepo.findByUserAndFlight(user,flight)).thenReturn(booking);
        Mockito.when(flightRepo.findById(flight.getFlightId())).thenReturn(Optional.of(flight));
        assertEquals("Booked Flight Cancelled Successfully",bookingService.cancelBooking(user,flight));
        Mockito.verify(flightRepo,Mockito.times(1)).save(flight);
        Mockito.verify(bookingRepo,Mockito.times(1)).save(Mockito.any(Booking.class));
    }

    @Test
    void invalidCancelBooking(){
        User user= new User();
        Flight flight= new Flight();
        flight.setFlightId(1L);
        flight.setAvailableSeats(50);
        Mockito.when(bookingRepo.findByUserAndFlight(user,flight)).thenReturn(null);
        assertEquals("Booking Not Found",bookingService.cancelBooking(user,flight));
    }
}