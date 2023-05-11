package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Plane;
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
    @Mock
    private PlaneRepo planeRepo;
    @Test
    void addPlane() {
        Plane plane= new Plane(30,"Harsh","Working");
        Mockito.when(planeRepo.save(plane)).thenReturn(plane);
        assertEquals(plane,planeService.addPlane(plane));
    }
    void InvalidaddPlane() {
        Plane plane= new Plane(30,"Harsh","Working");
        Mockito.when(planeRepo.save(plane)).thenReturn(plane);
        assertEquals(plane,planeService.addPlane(plane));
    }
    @Test
    void updatePlane() {

        Plane oldPlane = new Plane(50, "AirIndia","Not Wroking");

        UpdatePlanePayload updatedPlanePayload = new UpdatePlanePayload(1L, 40, "Akasa", "Working");

        Plane newPlane = new Plane();

        newPlane.setPlaneId(updatedPlanePayload.getPlaneId());
        newPlane.setCapacity(updatedPlanePayload.getCapacity());
        newPlane.setAirline(updatedPlanePayload.getAirline());
        newPlane.setStatus(updatedPlanePayload.getStatus());

        Mockito.when(planeRepo.save(newPlane)).thenReturn(newPlane);
        assertNotEquals(oldPlane, planeService.updatePlane(updatedPlanePayload));

    }

    @Test
    void setNewPlaneStatus() {

        String status = "Not Working";
        Long id = 1L;

//        Mockito.when(planeRepo.changePlaneStatus(status, id)).thenReturn();
//        assertEquals();



    }
}