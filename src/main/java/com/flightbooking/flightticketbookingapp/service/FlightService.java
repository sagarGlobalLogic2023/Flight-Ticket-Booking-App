package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.payload.CreateFlightPayload;
import com.flightbooking.flightticketbookingapp.payload.UpdateFlightPayload;
import com.flightbooking.flightticketbookingapp.repository.FlightRepo;
import com.flightbooking.flightticketbookingapp.repository.PlaneRepo;
import com.flightbooking.flightticketbookingapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepo flightRepo;
    @Autowired
    private PlaneRepo planeRepo;

    @Autowired
    private UserRepo userRepo;

    public String createFlight(CreateFlightPayload payload){
        Optional<Plane> plane = planeRepo.findById(payload.getPlaneId());
        Flight flight = new Flight();
        flight.setArrival(LocalDateTime.parse(payload.getArrival()));
        flight.setDeparture(LocalDateTime.parse(payload.getDeparture()));
        flight.setFare(payload.getFare());
        flight.setAvailableSeats(payload.getAvailableSeats());
        flight.setDestination(payload.getDestination());
        flight.setSource(payload.getSource());
        flight.setStatus("Confirmed");
        Long minutes = ChronoUnit.MINUTES.between(LocalDateTime.parse(payload.getDeparture()), LocalDateTime.parse(payload.getArrival()));
        flight.setDuration(minutes);
        if(plane.isPresent()){
            flight.setPlane(plane.get());
            flightRepo.save(flight);
            return "Flight Added Successfully";
        }
        else {
            return "Plane not found";
        }
       // planeId.ifPresent(flight::setPlane);

    }
    public List<Flight> listAllFlights(){
      List<Flight> allFlights= flightRepo.findAll();
      List<Flight> confirmedFlights= new ArrayList<>();
        for (Flight allFlight : allFlights) {
            if (allFlight.getStatus().equals("Confirmed"))
                confirmedFlights.add(allFlight);
        }
      return confirmedFlights;
    }

    public String updateFlight(UpdateFlightPayload updatePayload) {
        Optional<Plane> planeId = planeRepo.findById(updatePayload.getPlaneId());
        Optional<Flight> flight1 = flightRepo.findById(updatePayload.getFlightId());
        Flight flight = new Flight();
        if(flight1.isPresent()) {
            Long oldFlightId = flight1.get().getFlightId();
            flight.setFlightId(oldFlightId);
            flight.setArrival(LocalDateTime.parse(updatePayload.getArrival()));
            flight.setDeparture(LocalDateTime.parse(updatePayload.getDeparture()));
            flight.setFare(updatePayload.getFare());
            flight.setAvailableSeats(updatePayload.getAvailableSeats());
            flight.setDestination(updatePayload.getDestination());
            flight.setSource(updatePayload.getSource());
            flight.setStatus(updatePayload.getStatus());
            Long minutes = ChronoUnit.MINUTES.between(LocalDateTime.parse(updatePayload.getDeparture()), LocalDateTime.parse(updatePayload.getArrival()));
            flight.setDuration(minutes);
            if(planeId.isPresent()) {
                flight.setPlane(planeId.get());
                flightRepo.save(flight);
                return "Flight details updated";
            }
            return "Plane not found";
        }
        else{
            return "Flight not found";
        }
    }


    public String setNewFlightStatus(String status, Long id){
        Optional<Flight> flight= flightRepo.findById(id);
        if(flight.isPresent()) {
            flightRepo.changeFlightStatus(status, id);
            return "Status Changed Successfully";
        }
        else {
            return "Flight Not found";
        }
    }

    public List<Flight> SearchFlights(String source, String destination) {
        List<Flight> flights = flightRepo.findAllBySourceAndDestination(source, destination);
        List<Flight> confirmedFlights= new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getStatus().equals("Confirmed"))
                confirmedFlights.add(flight);
        }
        return confirmedFlights;
    }

    public Flight getFlight(Long flightId) {
        return  flightRepo.findById(flightId).get();
        //return flightRepo.getFlightById(flightId);
    }
}

