package com.flightbooking.flightticketbookingapp.controller;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.payload.*;
import com.flightbooking.flightticketbookingapp.service.FlightService;
import com.flightbooking.flightticketbookingapp.service.PlaneService;
import com.flightbooking.flightticketbookingapp.service.UserService;
import com.flightbooking.flightticketbookingapp.user.Role;
import com.flightbooking.flightticketbookingapp.user.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
class AdminControllerTest {

    AdminControllerTest() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Autowired
    private AdminController adminController;

    @MockBean
    private FlightService flightService;

    @MockBean
    private PlaneService planeService;

    @MockBean
    private UserService userService;


    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateFlight() throws Exception {
        CreateFlightPayload payload = new CreateFlightPayload();
        payload.setSource("Delhi");
        payload.setDestination("Mumbai");
        payload.setAvailableSeats(80);
        payload.setDeparture("2018-11-03T12:45:30");
        payload.setArrival("2018-11-03T12:45:30");
        payload.setPlaneId(4L);
        payload.setFare(2500);
        Mockito.when(flightService.createFlight(payload)).thenReturn("Flight Added Successfully");
        ResponseEntity<String> response = adminController.createFlight(payload);
        Mockito.verify(flightService, Mockito.times(1)).createFlight(payload);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Flight Added Successfully", response.getBody());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void invalidTestCreateFlight() throws Exception {
        CreateFlightPayload payload = new CreateFlightPayload();
        payload.setSource("Delhi");
        payload.setDestination("Mumbai");
        payload.setAvailableSeats(80);
        payload.setDeparture("2018-11-03T12:45:30");
        payload.setArrival("2018-11-03T12:45:30");
        payload.setPlaneId(4L);
        payload.setFare(2500);
        Mockito.when(flightService.createFlight(payload)).thenReturn("Plane not found");
        ResponseEntity<String> response = adminController.createFlight(payload);
        Mockito.verify(flightService, Mockito.times(1)).createFlight(payload);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Plane not found", response.getBody());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateFlight() {

        UpdateFlightPayload updatePayload = new UpdateFlightPayload();

        updatePayload.setSource("Delhi");
        updatePayload.setDestination("Mumbai");
        updatePayload.setAvailableSeats(80);
        updatePayload.setDeparture("2018-11-03T12:45:30");
        updatePayload.setArrival("2018-11-03T12:45:30");
        updatePayload.setPlaneId(4L);
        updatePayload.setFare(2500);

        Plane mockPlane = new Plane(40, "AirIndia", "Working");

        Flight mockFlight = new Flight("Agra", "Banglore", 40, 4000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 2L, mockPlane, "Confirmed");

       Mockito.when(flightService.updateFlight(updatePayload)).thenReturn("Flight details updated");

        ResponseEntity<String> response = adminController.updateFlight(updatePayload);

        Mockito.verify(flightService, Mockito.times(1)).updateFlight(any(UpdateFlightPayload.class));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Flight details updated", response.getBody());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void invalid1TestUpdateFlight() {

        UpdateFlightPayload updatePayload = new UpdateFlightPayload();

        updatePayload.setSource("Delhi");
        updatePayload.setDestination("Mumbai");
        updatePayload.setAvailableSeats(80);
        updatePayload.setDeparture("2018-11-03T12:45:30");
        updatePayload.setArrival("2018-11-03T12:45:30");
        updatePayload.setPlaneId(4L);
        updatePayload.setFare(2500);

        Plane mockPlane = new Plane(40, "AirIndia", "Working");

        Flight mockFlight = new Flight("Agra", "Banglore", 40, 4000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 2L, mockPlane, "Confirmed");

        Mockito.when(flightService.updateFlight(updatePayload)).thenReturn("Plane not found");

        ResponseEntity<String> response = adminController.updateFlight(updatePayload);

        Mockito.verify(flightService, Mockito.times(1)).updateFlight(any(UpdateFlightPayload.class));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Plane not found", response.getBody());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void invalid2TestUpdateFlight() {

        UpdateFlightPayload updatePayload = new UpdateFlightPayload();

        updatePayload.setSource("Delhi");
        updatePayload.setDestination("Mumbai");
        updatePayload.setAvailableSeats(80);
        updatePayload.setDeparture("2018-11-03T12:45:30");
        updatePayload.setArrival("2018-11-03T12:45:30");
        updatePayload.setPlaneId(4L);
        updatePayload.setFare(2500);

        Plane mockPlane = new Plane(40, "AirIndia", "Working");

        Flight mockFlight = new Flight("Agra", "Banglore", 40, 4000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 2L, mockPlane, "Confirmed");

        Mockito.when(flightService.updateFlight(updatePayload)).thenReturn("Flight not found");

        ResponseEntity<String> response = adminController.updateFlight(updatePayload);

        Mockito.verify(flightService, Mockito.times(1)).updateFlight(any(UpdateFlightPayload.class));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Flight not found", response.getBody());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void testChangeFlightStatus() {
        String status = "Confirmed";
        Long id = 1L;
        changeFlightStatusPayload changeFlightStatusPayload= new changeFlightStatusPayload(status,id);

       Mockito.when(flightService.setNewFlightStatus(status,id)).thenReturn("Status Changed Successfully");

        ResponseEntity<String> response = adminController.changeFlightStatus(changeFlightStatusPayload);

        Mockito.verify(flightService, Mockito.times(1)).setNewFlightStatus(any(String.class), any(Long.class));

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals("Status Changed Successfully", response.getBody());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void invalidTestChangeFlightStatus() {
        String status = "Confirmed";
        Long id = 1L;
        changeFlightStatusPayload changeFlightStatusPayload= new changeFlightStatusPayload(status,id);

        Mockito.when(flightService.setNewFlightStatus(status,id)).thenReturn("Flight Not found");

        ResponseEntity<String> response = adminController.changeFlightStatus(changeFlightStatusPayload);

        Mockito.verify(flightService, Mockito.times(1)).setNewFlightStatus(any(String.class), any(Long.class));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertEquals("Flight Not found", response.getBody());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreatePlane() {

        CreatePlanePayload payload = new CreatePlanePayload(120, "Air India", "Working");

        Plane mockPlane = new Plane();
        mockPlane.setCapacity(payload.getCapacity());
        mockPlane.setAirline(payload.getAirline());
        mockPlane.setStatus(payload.getStatus());

        when(planeService.addPlane(payload)).thenReturn(mockPlane);

        ResponseEntity<String> response = adminController.createPlane(payload);

        Mockito.verify(planeService, Mockito.times(1)).addPlane(payload);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("New Plane Added", response.getBody());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdatePlane() {

        UpdatePlanePayload updatePayload = new UpdatePlanePayload(4L, 40, "Akasa", "Working");

        Plane mockPlane = new Plane(40, "AirIndia", "Not Working");

        when(planeService.updatePlane(updatePayload)).thenReturn("Plane details updated");
        //Mockito.any(UpdatePlanePayload.class)

        ResponseEntity<String> response = adminController.updatePlane(updatePayload);

        Mockito.verify(planeService, Mockito.times(1)).updatePlane(updatePayload);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Plane details updated", response.getBody());


    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void invalidTestUpdatePlane() {

        UpdatePlanePayload updatePayload = new UpdatePlanePayload(4L, 40, "Akasa", "Working");

        Plane mockPlane = new Plane(40, "AirIndia", "Not Working");

        when(planeService.updatePlane(updatePayload)).thenReturn("Plane Not found");
        //Mockito.any(UpdatePlanePayload.class)

        ResponseEntity<String> response = adminController.updatePlane(updatePayload);

        Mockito.verify(planeService, Mockito.times(1)).updatePlane(updatePayload);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Plane Not found", response.getBody());


    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void testChangePlaneStatus() {

        String status = "Working";
        Long id = 1L;
        ChangePlaneStatusPayload changePlaneStatusPayload= new ChangePlaneStatusPayload(status,id);
        when(planeService.setNewPlaneStatus(status, id)).thenReturn("Status Changed Successfully");

        ResponseEntity<String> response = adminController.changePlaneStatus(changePlaneStatusPayload);

        Mockito.verify(planeService, Mockito.times(1)).setNewPlaneStatus(status, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals("Status Changed Successfully", response.getBody());

    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void invalidTestChangePlaneStatus() {

        String status = "Working";
        Long id = 1L;
        ChangePlaneStatusPayload changePlaneStatusPayload= new ChangePlaneStatusPayload(status,id);
        when(planeService.setNewPlaneStatus(status, id)).thenReturn("Plane Not Found");

        ResponseEntity<String> response = adminController.changePlaneStatus(changePlaneStatusPayload);

        Mockito.verify(planeService, Mockito.times(1)).setNewPlaneStatus(status, id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertEquals("Plane Not Found", response.getBody());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testSeeAllUsers() {

        List<User> mockUsers = new ArrayList<>();

        User u1 = new User();
        List<Booking> b1 = new ArrayList<>();
        mockUsers.add(new User(1L, "Mohit", "Sharma", "mohit@gl.com", "1234", "No", b1, Role.ROLE_ADMIN));
        User u2 = new User();
        List<Booking> b2 = new ArrayList<>();
        mockUsers.add(new User(2L, "Raghav", "Sharma", "rg@gl.com", "4321", "yes", b2, Role.ROLE_USER));

        when(userService.listAllUsers()).thenReturn(mockUsers);

        ResponseEntity<List<User>> response = adminController.seeAllUsers();

        Mockito.verify(userService, Mockito.times(1)).listAllUsers();

        // Verify the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());


    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateUser() {

        UpdateUserPayload updatePayload = new UpdateUserPayload(1L, "mohit@gl.com", "1234", "Mohit", "Sharma","No","Role.ROLE_USER");

        List<Booking>bookings= new ArrayList<>();
        User mockUser = new User(1L, "Raghav", "Sharma", "rg@gl.com", "4321","No", bookings,Role.ROLE_USER);

        Mockito.when(userService.updateUser(updatePayload)).thenReturn("User Details Updated");

//        Mockito.when(userService.updateUser(Mockito.any(UpdateUserPayload.class)));

        ResponseEntity<String> response = adminController.updateUser(updatePayload);

        Mockito.verify(userService, Mockito.times(1)).updateUser(Mockito.any(UpdateUserPayload.class));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User Details Updated", response.getBody());

    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void invalidTestUpdateUser() {

        UpdateUserPayload updatePayload = new UpdateUserPayload(1L, "mohit@gl.com", "1234", "Mohit", "Sharma","No","Role.ROLE_USER");

        List<Booking>bookings= new ArrayList<>();
        User mockUser = new User(1L, "Raghav", "Sharma", "rg@gl.com", "4321","No", bookings,Role.ROLE_USER);

        Mockito.when(userService.updateUser(updatePayload)).thenReturn("User Not Found");

//        Mockito.when(userService.updateUser(Mockito.any(UpdateUserPayload.class)));

        ResponseEntity<String> response = adminController.updateUser(updatePayload);

        Mockito.verify(userService, Mockito.times(1)).updateUser(Mockito.any(UpdateUserPayload.class));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User Not Found", response.getBody());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testBlockUser() {

        String status = "No";
        Long id = 1L;
        BlockUserPayload blockUserPayload= new BlockUserPayload(status,id);

        Mockito.when(userService.blockUser(status, id)).thenReturn("User Blocked Successfully");

        ResponseEntity<String> response = adminController.blockUser(blockUserPayload);

        Mockito.verify(userService, Mockito.times(1)).blockUser(status, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals("User Blocked Successfully", response.getBody());


    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void invalid1TestBlockUser() {

        String status = "No";
        Long id = 1L;
        BlockUserPayload blockUserPayload= new BlockUserPayload(status,id);

        Mockito.when(userService.blockUser(status, id)).thenReturn("User Id invalid");

        ResponseEntity<String> response = adminController.blockUser(blockUserPayload);

        Mockito.verify(userService, Mockito.times(1)).blockUser(status, id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertEquals("User Id invalid", response.getBody());


    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void invalid2TestBlockUser() {

        String status = "No";
        Long id = 1L;
        BlockUserPayload blockUserPayload= new BlockUserPayload(status,id);

        Mockito.when(userService.blockUser(status, id)).thenReturn("User does not Exist");

        ResponseEntity<String> response = adminController.blockUser(blockUserPayload);

        Mockito.verify(userService, Mockito.times(1)).blockUser(status, id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertEquals("User does not Exist", response.getBody());


    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void seeAllFlights() {
        List<Flight> mockFlights = new ArrayList<>();

        Plane p1 = new Plane();
        mockFlights.add(new Flight("Source 1", "Destination 1", 40, 4000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 2L, p1, "Confirmed"));
        Plane p2 = new Plane();
        mockFlights.add(new Flight("Source 2", "Destination 2", 25, 6500, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 4L, p2, "Confirmed"));

        Mockito.when(flightService.listAllFlights()).thenReturn(mockFlights);

       ResponseEntity<List<Flight>> response = adminController.seeAllFlights();

        Mockito.verify(flightService,Mockito.times(1)).listAllFlights();

        assertEquals(response, adminController.seeAllFlights());
    }
}