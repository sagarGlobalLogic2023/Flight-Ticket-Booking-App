package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.payload.ChangePlaneStatusPayload;
import com.flightbooking.flightticketbookingapp.payload.CreatePlanePayload;
import com.flightbooking.flightticketbookingapp.payload.UpdatePlanePayload;
import com.flightbooking.flightticketbookingapp.repository.PlaneRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PlaneServiceTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private PlaneService planeService;
    @MockBean
    private PlaneRepo planeRepo;
    @Test
    void addPlane() {
        CreatePlanePayload createPlanePayload= new CreatePlanePayload(30,"Indica","Working");
        Plane plane= new Plane();
        plane.setCapacity(createPlanePayload.getCapacity());
        plane.setAirline(createPlanePayload.getAirline());
        plane.setStatus(createPlanePayload.getStatus());
        Mockito.when(planeRepo.save(plane)).thenReturn(plane);
        assertEquals(plane,planeService.addPlane(createPlanePayload));
    }

    @Test
    void updatePlane() {
        Plane oldPlane= new Plane();
        oldPlane.setPlaneId(1L);
        oldPlane.setStatus("working");
        oldPlane.setCapacity(100);
        oldPlane.setAirline("Spicejet");
        UpdatePlanePayload updatedPlanePayload = new UpdatePlanePayload(1L, 50, "AirIndia", "Not Working");
        Plane newPlane = new Plane();
        newPlane.setPlaneId(updatedPlanePayload.getPlaneId());
        newPlane.setCapacity(updatedPlanePayload.getCapacity());
        newPlane.setAirline(updatedPlanePayload.getAirline());
        newPlane.setStatus(updatedPlanePayload.getStatus());
        Mockito.when(planeRepo.save(newPlane)).thenReturn(newPlane);
        Mockito.when(planeRepo.findById(updatedPlanePayload.getPlaneId())).thenReturn(Optional.of(oldPlane));
        assertEquals("Plane details updated", planeService.updatePlane(updatedPlanePayload));
    }
    @Test
    void invalidUpdatePlane() {
        Plane oldPlane= new Plane();
        oldPlane.setPlaneId(1L);
        oldPlane.setStatus("working");
        oldPlane.setCapacity(100);
        oldPlane.setAirline("Spicejet");
        UpdatePlanePayload updatedPlanePayload = new UpdatePlanePayload(1L, 50, "AirIndia", "Not Working");
        Plane newPlane = new Plane();
        newPlane.setPlaneId(updatedPlanePayload.getPlaneId());
        newPlane.setCapacity(updatedPlanePayload.getCapacity());
        newPlane.setAirline(updatedPlanePayload.getAirline());
        newPlane.setStatus(updatedPlanePayload.getStatus());
        Mockito.when(planeRepo.findById(updatedPlanePayload.getPlaneId())).thenReturn(Optional.empty());
        assertEquals("Plane Not found", planeService.updatePlane(updatedPlanePayload));
    }

    @Test
    void setNewPlaneStatus() {
        ChangePlaneStatusPayload changePlaneStatusPayload= new ChangePlaneStatusPayload();
        changePlaneStatusPayload.setId(1L);
        changePlaneStatusPayload.setStatus("Not Working");
        Plane plane= new Plane(100,"Spicejet","Working");
        plane.setPlaneId(1L);
        Plane newPlane= new Plane(100,"Spicejet","Not Working");
        newPlane.setPlaneId(1L);
        Mockito.when(planeRepo.findById(changePlaneStatusPayload.getId())).thenReturn(Optional.of(plane));
        assertEquals("Status Changed Successfully",planeService.setNewPlaneStatus(changePlaneStatusPayload.getStatus(),changePlaneStatusPayload.getId()));
    }
    @Test
    void invalidSetNewPlaneStatus() {
        ChangePlaneStatusPayload changePlaneStatusPayload= new ChangePlaneStatusPayload();
        changePlaneStatusPayload.setId(1L);
        changePlaneStatusPayload.setStatus("Not Working");
        Plane plane= new Plane(100,"Spicejet","Working");
        plane.setPlaneId(1L);
        Plane newPlane= new Plane(100,"Spicejet","Not Working");
        newPlane.setPlaneId(1L);
        Mockito.when(planeRepo.findById(changePlaneStatusPayload.getId())).thenReturn(Optional.empty());
        assertEquals("Plane Not Found",planeService.setNewPlaneStatus(changePlaneStatusPayload.getStatus(),changePlaneStatusPayload.getId()));
    }
}