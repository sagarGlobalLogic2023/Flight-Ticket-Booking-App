package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.payload.*;
import com.flightbooking.flightticketbookingapp.repository.PlaneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaneService {
    @Autowired
    private PlaneRepo planeRepo;

    public Plane addPlane(CreatePlanePayload createPlanePayload){
        Plane plane= new Plane();
        plane.setCapacity(createPlanePayload.getCapacity());
        plane.setStatus(createPlanePayload.getStatus());
        plane.setAirline(createPlanePayload.getAirline());
       return planeRepo.save(plane);
    }

    public String updatePlane(UpdatePlanePayload updatePlanePayload) {
        Plane plane = new Plane();

        Optional<Plane> updatedPlane = planeRepo.findById(updatePlanePayload.getPlaneId());
        if (updatedPlane.isPresent()) {
            plane.setPlaneId(updatedPlane.get().getPlaneId());
            plane.setCapacity(updatePlanePayload.getCapacity());
            plane.setAirline(updatePlanePayload.getAirline());
            plane.setStatus(updatePlanePayload.getStatus());
            planeRepo.save(plane);
            return "Plane details updated";
        } else {
            return "Plane Not found";
        }
    }
    public String setNewPlaneStatus(String status, Long id){
        Optional<Plane>plane= planeRepo.findById(id);
        if(plane.isPresent()){
        planeRepo.changePlaneStatus(status, id);
        return "Status Changed Successfully";
    }
        else {
            return "Plane Not Found";
        }
}
}
