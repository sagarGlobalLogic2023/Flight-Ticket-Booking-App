package com.flightbooking.flightticketbookingapp.controller;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.entity.BookingId;
import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.payload.*;
import com.flightbooking.flightticketbookingapp.repository.FlightRepo;
import com.flightbooking.flightticketbookingapp.service.BookingService;
import com.flightbooking.flightticketbookingapp.service.FlightService;
import com.flightbooking.flightticketbookingapp.service.UserService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
class UserControllerTest {
    @Autowired
    private UserController userController;
    @MockBean
    private FlightService flightService;
    @MockBean
    private BookingService bookingService;
    @MockBean
    private UserService userService;

    @MockBean
    private FlightRepo flightRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockUser(roles = "USER")
    void searchFlights() {
        SearchFlightPayload searchFlightPayload= new SearchFlightPayload("Source 1","Destination 1");
        List<Flight> mockFlights = new ArrayList<>();

        Plane p1 = new Plane();
        mockFlights.add(new Flight("Source 1", "Destination 1", 40, 4000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 2L, p1, "Confirmed"));
        Plane p2 = new Plane();
        mockFlights.add(new Flight("Source 1", "Destination 1", 25, 6500, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 4L, p2, "Confirmed"));
        Mockito.when(flightService.SearchFlights(searchFlightPayload.getSource(),searchFlightPayload.getDestination())).thenReturn(mockFlights);
        assertEquals(userController.searchFlights(searchFlightPayload), ResponseEntity.status(HttpStatus.OK).body(mockFlights));
    }
    @Test
    @WithMockUser(roles = "USER")
    void invalidSearchFlights() {
        SearchFlightPayload searchFlightPayload= new SearchFlightPayload("Source 1","Destination 1");
        List<Flight>NoFlights= new ArrayList<>();
        Mockito.when(flightService.SearchFlights(searchFlightPayload.getSource(),searchFlightPayload.getDestination())).thenReturn(NoFlights);
        assertEquals(userController.searchFlights(searchFlightPayload), ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Flights Found"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void showBookings() {
        User user= new User();
        user.setUserId(1L);
        Flight flight= new Flight();
        List<Booking> bookings= new ArrayList<>();
        bookings.add(new Booking(new BookingId(1L,1L, LocalDateTime.parse("2017-01-13T17:09:42.411")),user,flight,LocalDateTime.parse("2017-01-13T17:09:42.411"),40,"Confirmed"));
        bookings.add(new Booking(new BookingId(1L,1L, LocalDateTime.parse("2020-01-22T20:10:42.411")),user,flight,LocalDateTime.parse("2020-01-22T20:10:42.411"),45,"Confirmed"));
        showBookingsPayload showBookingsPayload= new showBookingsPayload(user);
        Mockito.when(bookingService.showBookings(showBookingsPayload.getUser())).thenReturn(bookings);
        assertEquals(userController.showBookings(showBookingsPayload),ResponseEntity.status(HttpStatus.OK).body(bookings));
        Mockito.verify(bookingService,Mockito.times(1)).showBookings(showBookingsPayload.getUser());
    }
    @Test
    @WithMockUser(roles = "USER")
    void invalidShowBookings() {
        User user= new User();
        user.setUserId(1L);
        Flight flight= new Flight();
        List<Booking> bookings= new ArrayList<>();
        showBookingsPayload showBookingsPayload= new showBookingsPayload(user);
        Mockito.when(bookingService.showBookings(showBookingsPayload.getUser())).thenReturn(bookings);
        assertEquals(userController.showBookings(showBookingsPayload),ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking Not Found for this UserID"));
        Mockito.verify(bookingService,Mockito.times(1)).showBookings(showBookingsPayload.getUser());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getBooking() {
        User user= new User();
        user.setUserId(1L);
        Flight flight= new Flight();
        flight.setFlightId(1L);
        GetBookingPayload getBookingPayload= new GetBookingPayload(user,flight);
        List<Booking> bookings= new ArrayList<>();
        bookings.add(new Booking(new BookingId(1L,1L, LocalDateTime.parse("2017-01-13T17:09:42.411")),user,flight,LocalDateTime.parse("2017-01-13T17:09:42.411"),40,"Confirmed"));
        bookings.add(new Booking(new BookingId(1L,1L, LocalDateTime.parse("2020-01-22T20:10:42.411")),user,flight,LocalDateTime.parse("2020-01-22T20:10:42.411"),45,"Confirmed"));
        Mockito.when(bookingService.getBook(getBookingPayload.getUser(),getBookingPayload.getFlight())).thenReturn(bookings);
        assertEquals(userController.getBooking(getBookingPayload),ResponseEntity.status(HttpStatus.OK).body(bookings));
        Mockito.verify(bookingService,Mockito.times(1)).getBook(getBookingPayload.getUser(),getBookingPayload.getFlight());

    }
    @Test
    @WithMockUser(roles = "USER")
    void invalidGetBooking() {
        User user= new User();
        user.setUserId(1L);
        Flight flight= new Flight();
        flight.setFlightId(1L);
        GetBookingPayload getBookingPayload= new GetBookingPayload(user,flight);
        List<Booking> bookings= new ArrayList<>();
        Mockito.when(bookingService.getBook(getBookingPayload.getUser(),getBookingPayload.getFlight())).thenReturn(bookings);
        assertEquals(userController.getBooking(getBookingPayload),ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Bookings found"));
        Mockito.verify(bookingService,Mockito.times(1)).getBook(getBookingPayload.getUser(),getBookingPayload.getFlight());
    }

    @Test
    @WithMockUser(roles = "USER")
    void bookFlight() {
        BookFlightPayload bookFlightPayload= new BookFlightPayload();
        bookFlightPayload.setUserId(1L);
        bookFlightPayload.setFlightId(1L);
        bookFlightPayload.setSeatNumber(100);
        Flight flight=new Flight();
        flight.setFlightId(1L);
        flight.setAvailableSeats(100);
        Mockito.when(flightRepo.findById(bookFlightPayload.getFlightId())).thenReturn(Optional.of(flight));
        Mockito.when(flightRepo.save(Mockito.any(Flight.class))).thenReturn(Mockito.any(Flight.class));
        assertEquals(userController.bookFlight(bookFlightPayload),ResponseEntity.status(HttpStatus.CREATED).body("Booking Done"));
        Mockito.verify(bookingService,Mockito.times(1)).bookFlight(bookFlightPayload);
    }
    @Test
    @WithMockUser(roles = "USER")
    void invalid1BookFlight() {
        BookFlightPayload bookFlightPayload= new BookFlightPayload();
        bookFlightPayload.setUserId(1L);
        bookFlightPayload.setFlightId(1L);
        bookFlightPayload.setSeatNumber(100);
        Flight flight=new Flight();
        flight.setFlightId(1L);
        flight.setAvailableSeats(0);
        Mockito.when(flightRepo.findById(bookFlightPayload.getFlightId())).thenReturn(Optional.of(flight));
        Mockito.when(flightRepo.save(Mockito.any(Flight.class))).thenReturn(Mockito.any(Flight.class));
        assertEquals(userController.bookFlight(bookFlightPayload),ResponseEntity.status(HttpStatus.FORBIDDEN).body("Seats Full , no more bookings possible!"));
    }
    @Test
    @WithMockUser(roles = "USER")
    void invalid2BookFlight() {
        BookFlightPayload bookFlightPayload= new BookFlightPayload();
        bookFlightPayload.setUserId(1L);
        bookFlightPayload.setFlightId(1L);
        bookFlightPayload.setSeatNumber(100);
        Flight flight=new Flight();
        flight.setFlightId(1L);
        flight.setAvailableSeats(0);
        Mockito.when(flightRepo.findById(bookFlightPayload.getFlightId())).thenReturn(Optional.empty());
        Mockito.when(flightRepo.save(Mockito.any(Flight.class))).thenReturn(Mockito.any(Flight.class));
        assertEquals(userController.bookFlight(bookFlightPayload),ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found!"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void updateUser() {
        UpdateProfilePayload updateProfilePayload= new UpdateProfilePayload(1L, "Raghav@gl.com", "12345", "Raghav", "Sharma");
        Mockito.when(userService.updateProfile(updateProfilePayload)).thenReturn("Profile updated successfully");
        assertEquals(userController.updateUser(updateProfilePayload),ResponseEntity.status(HttpStatus.OK).body("Profile updated successfully"));
        Mockito.verify(userService,Mockito.times(1)).updateProfile(updateProfilePayload);
    }
    @Test
    @WithMockUser(roles = "USER")
    void invalidUpdateUser() {
        UpdateProfilePayload updateProfilePayload= new UpdateProfilePayload(1L, "Raghav@gl.com", "12345", "Raghav", "Sharma");
        Mockito.when(userService.updateProfile(updateProfilePayload)).thenReturn("Error Updating Profile, user not found");
        assertEquals(userController.updateUser(updateProfilePayload),ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error Updating Profile, user not found"));
        Mockito.verify(userService,Mockito.times(1)).updateProfile(updateProfilePayload);
    }

    @Test
    @WithMockUser(roles = "USER")
    void cancelBooking() {
        User user= new User();
        user.setUserId(1L);
        Flight flight= new Flight();
        flight.setFlightId(1L);
        CancelBookingPayload cancelBookingPayload= new CancelBookingPayload(user,flight);
        Mockito.when(bookingService.cancelBooking(cancelBookingPayload.getUser(),cancelBookingPayload.getFlight())).thenReturn("Booked Flight Cancelled Successfully");
        assertEquals(userController.cancelBooking(cancelBookingPayload),ResponseEntity.status(HttpStatus.OK).body("Booked Flight Cancelled Successfully"));
        Mockito.verify(bookingService,Mockito.times(1)).cancelBooking(cancelBookingPayload.getUser(),cancelBookingPayload.getFlight());
    }
    @Test
    @WithMockUser(roles = "USER")
    void invalidCancelBooking() {
        User user= new User();
        user.setUserId(1L);
        Flight flight= new Flight();
        flight.setFlightId(1L);
        CancelBookingPayload cancelBookingPayload= new CancelBookingPayload(user,flight);
        Mockito.when(bookingService.cancelBooking(cancelBookingPayload.getUser(),cancelBookingPayload.getFlight())).thenReturn("Booking Not Found");
        assertEquals(userController.cancelBooking(cancelBookingPayload),ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking Not Found"));
        Mockito.verify(bookingService,Mockito.times(1)).cancelBooking(cancelBookingPayload.getUser(),cancelBookingPayload.getFlight());
    }
}