package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.entity.User;
import com.flightbooking.flightticketbookingapp.payload.CreateFlightPayload;
import com.flightbooking.flightticketbookingapp.payload.UpdateFlightPayload;
import com.flightbooking.flightticketbookingapp.repository.FlightRepo;
import com.flightbooking.flightticketbookingapp.repository.PlaneRepo;
import com.flightbooking.flightticketbookingapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
//    private FlightRepository flightRepository;
//
//    @Autowired
//    public FlightService(FlightRepository flightRepository) {
//        this.flightRepository = flightRepository;
//    }
//
//    public Flight addFlight(CreateFlight createFlight) {
//        //createFlight.g
//        return null;
//    }
    @Autowired
    private FlightRepo flightRepo;
    @Autowired
    private PlaneRepo planeRepo;

    @Autowired
    private UserRepo userRepo;

    public void createFlight(CreateFlightPayload payload){
        Optional<Plane> planeId = planeRepo.findById(payload.getPlaneId());
        Flight flight = new Flight();
        flight.setArrival(LocalDateTime.parse(payload.getArrival()));
        flight.setDeparture(LocalDateTime.parse(payload.getDeparture()));
        flight.setFare(payload.getFare());
        flight.setAvailableSeats(payload.getAvailableSeats());
        flight.setDestination(payload.getDestination());
        flight.setSource(payload.getSource());
        flight.setStatus("Confirmed");
        Long hours = ChronoUnit.HOURS.between(LocalDateTime.parse(payload.getDeparture()), LocalDateTime.parse(payload.getArrival()));
        flight.setDuration(hours);
        planeId.ifPresent(flight::setPlane);
        flightRepo.save(flight);
    }
    public List<Flight> listAllFlights(){
      List<Flight> allFlights= flightRepo.findAll();
      return allFlights;
    }

    public void updateFlight(UpdateFlightPayload updatePayload) {
        Optional<Plane> planeId = planeRepo.findById(updatePayload.getPlaneId());
        Optional<Flight> flightId = flightRepo.findById(updatePayload.getFlightId());

        Long oldFlightId = flightId.get().getFlightId();

        Flight flight = new Flight();
//        flightId.ifPresent(value -> flight.setFlightId(value.getFlightId()));
        flight.setFlightId(oldFlightId);
        flight.setArrival(LocalDateTime.parse(updatePayload.getArrival()));
        flight.setDeparture(LocalDateTime.parse(updatePayload.getDeparture()));
        flight.setFare(updatePayload.getFare());
        flight.setAvailableSeats(updatePayload.getAvailableSeats());
        flight.setDestination(updatePayload.getDestination());
        flight.setSource(updatePayload.getSource());
        flight.setStatus(updatePayload.getStatus());
        Long hours = ChronoUnit.HOURS.between(LocalDateTime.parse(updatePayload.getDeparture()), LocalDateTime.parse(updatePayload.getArrival()));
        flight.setDuration(hours);
        planeId.ifPresent(flight::setPlane);
        flightRepo.save(flight);

    }


    public void setNewFlightStatus(String status, Long id){
        flightRepo.changeFlightStatus(status, id);
    }

    public List<Flight> SearchFlights(String source, String destination) {
        List<Flight> flights = flightRepo.findAllBySourceAndDestination(source, destination);
        return flights;
    }


    public Flight getFlight(Long flightId) {
        return flightRepo.findById(flightId).get();
    }
}

