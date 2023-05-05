package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.payload.UpdateFlightPayload;
import com.flightbooking.flightticketbookingapp.payload.UpdatePlanePayload;
import com.flightbooking.flightticketbookingapp.payload.UpdateUserPayload;
import com.flightbooking.flightticketbookingapp.repository.PlaneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaneService {
    @Autowired
    private PlaneRepo planeRepo;

    public void addPlane(Plane plane){
        planeRepo.save(plane);
    }

    public void updatePlane(UpdatePlanePayload updatePlanePayload){
        Plane plane = new Plane();

        Optional<Plane> updatedPlane = planeRepo.findById(updatePlanePayload.getPlaneId());

        if(updatedPlane.isPresent()) {
            Long planeId = updatedPlane.get().getPlaneId();
            plane.setPlaneId(planeId);
        }
        //updatedPlane.ifPresent(value -> plane.setPlaneId(value.getPlaneId()));
        plane.setCapacity(updatePlanePayload.getCapacity());
        plane.setAirline(updatePlanePayload.getAirline());
        plane.setStatus(updatePlanePayload.getStatus());
        planeRepo.save(plane);
    }
    public void setNewPlaneStatus(String status, Long id){
        planeRepo.changePlaneStatus(status, id);
    }

}
