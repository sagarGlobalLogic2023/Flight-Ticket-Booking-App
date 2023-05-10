package com.flightbooking.flightticketbookingapp.controller;

import com.flightbooking.flightticketbookingapp.payload.CreateFlightPayload;
import com.flightbooking.flightticketbookingapp.service.FlightService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {


    AdminController adminController;


    @BeforeEach
    void setUp() {
        adminController = new AdminController();
    }

    @AfterEach
    void tearDown() {
    }
    @Mock
    FlightService service;
    @Test
    void createFlight() {
        CreateFlightPayload createFlightPayload = new CreateFlightPayload("Delhi","Mumbai",10,"2017-01-13T17:09:42.411","2017-01-14T19:09:42.411", 1L,20);
     // Mockito.when(service.createFlight(createFlightPayload)).thenReturn();
//        AdminController adminController1= Mockito.mock(AdminController.class);
//        Mockito.when(adminController1.createFlight(createFlightPayload)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Flight Added Successfully"));
      //  ResponseEntity<String> response= adminController.createFlight(createFlightPayload);
        //assertEquals(ResponseEntity.status(HttpStatus.CREATED).body("Flight Added Successfully"),response);
    }

    @Test
    void seeAllFlights() {
    }

    @Test
    void updateFlight() {
    }

    @Test
    void changeFlightStatus() {
    }

    @Test
    void createPlane() {
    }

    @Test
    void updatePlane() {
    }

    @Test
    void changePlaneStatus() {
    }

    @Test
    void seeAllUsers() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void blockUser() {
    }
}