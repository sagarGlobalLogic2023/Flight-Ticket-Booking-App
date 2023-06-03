package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.payload.CreateFlightPayload;
import com.flightbooking.flightticketbookingapp.payload.UpdateFlightPayload;
import com.flightbooking.flightticketbookingapp.repository.FlightRepo;
import com.flightbooking.flightticketbookingapp.repository.PlaneRepo;
import com.flightbooking.flightticketbookingapp.repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class FlightServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private FlightService flightService;


    @MockBean
    private UserRepo userRepo;

    @MockBean
    private FlightRepo flightRepository;

    @MockBean
    private PlaneRepo planeRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createFlight() {
        CreateFlightPayload payload = new CreateFlightPayload();
        payload.setSource("Delhi");
        payload.setDestination("Mumbai");
        payload.setAvailableSeats(80);
        payload.setDeparture("2018-11-03T12:45:30");
        payload.setArrival("2018-11-03T12:45:30");
        payload.setPlaneId(4L);
        payload.setFare(2500);

        Plane mockPlane = new Plane(120, "Akasa", "Working");

        Mockito.when(planeRepository.findById(payload.getPlaneId())).thenReturn(Optional.of(mockPlane));

        assertEquals("Flight Added Successfully",flightService.createFlight(payload));
        // Verify that the planeRepository.findById method was called with the correct planeId
        Mockito.verify(planeRepository).findById(payload.getPlaneId());

        // Verify that the flightRepository.save method was called with the expected Flight object
        Mockito.verify(flightRepository).save(Mockito.any(Flight.class));
    }
    @Test
    void invalidCreateFlight() {
        CreateFlightPayload payload = new CreateFlightPayload();
        payload.setSource("Delhi");
        payload.setDestination("Mumbai");
        payload.setAvailableSeats(80);
        payload.setDeparture("2018-11-03T12:45:30");
        payload.setArrival("2018-11-03T12:45:30");
        payload.setPlaneId(4L);
        payload.setFare(2500);

        Plane mockPlane = new Plane(120, "Akasa", "Working");

        Mockito.when(planeRepository.findById(payload.getPlaneId())).thenReturn(Optional.empty());

        assertEquals("Plane not found",flightService.createFlight(payload));
        // Verify that the planeRepository.findById method was called with the correct planeId
        Mockito.verify(planeRepository,Mockito.times(1)).findById(payload.getPlaneId());
    }

    @Test
    void listAllFlights() {
        List<Flight> mockFlights = new ArrayList<>();

        Plane p1 = new Plane();
        mockFlights.add(new Flight("Source 1", "Destination 1", 40, 4000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 2L, p1, "Confirmed"));
        Plane p2 = new Plane();
        mockFlights.add(new Flight("Source 2", "Destination 2", 25, 6500, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 4L, p2, "Confirmed"));

        Mockito.when(flightRepository.findAll()).thenReturn(mockFlights);

        List<Flight> result = flightService.listAllFlights();

        // Verify that the repository method was called
        Mockito.verify(flightRepository).findAll();

        // Verify that the returned list of flights is the same as the mockFlights
        assertEquals(mockFlights, result);
    }

    @Test
    void updateFlight() {

        UpdateFlightPayload updatePayload = new UpdateFlightPayload();

        updatePayload.setFlightId(1L);
        updatePayload.setSource("Banglore");
        updatePayload.setDestination("Srinagar");
        updatePayload.setAvailableSeats(60);
        updatePayload.setDeparture("2018-11-03T12:45:30");
        updatePayload.setArrival("2018-11-03T12:45:30");
        updatePayload.setPlaneId(5L);
        updatePayload.setFare(7500);
        updatePayload.setStatus("Confirmed");


        Plane mockPlane = new Plane(40, "AirIndia", "Working");

        Flight mockFlight = new Flight("Source 1", "Destination 1", 40, 4000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 2L, mockPlane, "Confirmed");

        // Mock 1
        Mockito.when(planeRepository.findById(updatePayload.getPlaneId())).thenReturn(Optional.of(mockPlane));

        // Mock 2
        Mockito.when(flightRepository.findById(updatePayload.getFlightId())).thenReturn(Optional.of(mockFlight));

        assertEquals("Flight details updated",flightService.updateFlight(updatePayload));

        // Verify that the planeRepository.findById method was called with the correct planeId
        Mockito.verify(planeRepository).findById(updatePayload.getPlaneId());

        // Verify that the flightRepository.findById method was called with the correct flightId
        Mockito.verify(flightRepository).findById(updatePayload.getFlightId());

        // Verify that the flightRepository.save method was called with the expected Flight object
        Mockito.verify(flightRepository).save(Mockito.any(Flight.class));
    }
    @Test
    void invalidUpdateFlight() {

        UpdateFlightPayload updatePayload = new UpdateFlightPayload();

        updatePayload.setFlightId(1L);
        updatePayload.setSource("Banglore");
        updatePayload.setDestination("Srinagar");
        updatePayload.setAvailableSeats(60);
        updatePayload.setDeparture("2018-11-03T12:45:30");
        updatePayload.setArrival("2018-11-03T12:45:30");
        updatePayload.setPlaneId(5L);
        updatePayload.setFare(7500);
        updatePayload.setStatus("Confirmed");


        Plane mockPlane = new Plane(40, "AirIndia", "Working");

        Flight mockFlight = new Flight("Source 1", "Destination 1", 40, 4000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 2L, mockPlane, "Confirmed");

        // Mock 1
        Mockito.when(planeRepository.findById(updatePayload.getPlaneId())).thenReturn(Optional.empty());

        // Mock 2
        Mockito.when(flightRepository.findById(updatePayload.getFlightId())).thenReturn(Optional.of(mockFlight));

        assertEquals("Plane not found",flightService.updateFlight(updatePayload));

        // Verify that the planeRepository.findById method was called with the correct planeId
        Mockito.verify(planeRepository).findById(updatePayload.getPlaneId());

        // Verify that the flightRepository.findById method was called with the correct flightId
        Mockito.verify(flightRepository).findById(updatePayload.getFlightId());
    }
    @Test
    void invalid2UpdateFlight() {

        UpdateFlightPayload updatePayload = new UpdateFlightPayload();

        updatePayload.setFlightId(1L);
        updatePayload.setSource("Banglore");
        updatePayload.setDestination("Srinagar");
        updatePayload.setAvailableSeats(60);
        updatePayload.setDeparture("2018-11-03T12:45:30");
        updatePayload.setArrival("2018-11-03T12:45:30");
        updatePayload.setPlaneId(5L);
        updatePayload.setFare(7500);
        updatePayload.setStatus("Confirmed");


        Plane mockPlane = new Plane(40, "AirIndia", "Working");

        Flight mockFlight = new Flight("Source 1", "Destination 1", 40, 4000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 2L, mockPlane, "Confirmed");

        // Mock 1
        Mockito.when(planeRepository.findById(updatePayload.getPlaneId())).thenReturn(Optional.of(mockPlane));

        // Mock 2
        Mockito.when(flightRepository.findById(updatePayload.getFlightId())).thenReturn(Optional.empty());

        assertEquals("Flight not found",flightService.updateFlight(updatePayload));

        // Verify that the planeRepository.findById method was called with the correct planeId
        Mockito.verify(planeRepository).findById(updatePayload.getPlaneId());

        // Verify that the flightRepository.findById method was called with the correct flightId
        Mockito.verify(flightRepository).findById(updatePayload.getFlightId());
    }

    @Test
    void setNewFlightStatus() {

        String status = "Confirmed";
        Long id = 1L;
        Flight flight= new Flight();
        flight.setFlightId(id);
        flight.setStatus("Cancelled");
        Mockito.when(flightRepository.findById(id)).thenReturn(Optional.of(flight));
        assertEquals("Status Changed Successfully",flightService.setNewFlightStatus(status, id));
        // Verify that the repository method was called with the correct parameters
        Mockito.verify(flightRepository,Mockito.times(1)).changeFlightStatus(status, id);

    }
    @Test
    void invalidSetNewFlightStatus() {

        String status = "Confirmed";
        Long id = 1L;
        Flight flight= new Flight();
        flight.setFlightId(id);
        flight.setStatus("Cancelled");
        Mockito.when(flightRepository.findById(id)).thenReturn(Optional.empty());
        assertEquals("Flight Not found",flightService.setNewFlightStatus(status, id));
    }

    @Test
    void searchFlights() {
        List<Flight> mockFlights = new ArrayList<>();

        Plane p1 = new Plane();
        mockFlights.add(new Flight("Source 1", "Destination 1", 40, 4000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 2L, p1, "Confirmed"));

        Plane p2 = new Plane();
        mockFlights.add(new Flight("Source 1", "Destination 1", 60, 6000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 3L, p2, "Confirmed"));
        Mockito.when(flightRepository.findAllBySourceAndDestination("Source 1", "Destination 1")).thenReturn(mockFlights);

        List<Flight> result = flightService.SearchFlights("Source 1", "Destination 1");

        assertEquals(mockFlights, result);
        assertEquals(2, result.size());
    }

    @Test
    void getFlight() {
        Plane p1 = new Plane();
        Flight mockFlight = new Flight("Source 1", "Destination 1", 40, 4000, LocalDateTime.parse("2018-11-03T12:45:30"), LocalDateTime.parse("2018-11-03T12:45:30"), 2L, p1, "Confirmed");

        Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(mockFlight));

        Flight result = flightService.getFlight(1L);

        // Verify that the repository method was called with the correct flightId
        Mockito.verify(flightRepository).findById(1L);

        // Verify that the returned Flight object is the same as the mockFlight
        assertEquals(mockFlight, result);
    }
}