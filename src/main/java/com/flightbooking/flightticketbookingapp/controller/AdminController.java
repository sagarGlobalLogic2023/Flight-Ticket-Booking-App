package com.flightbooking.flightticketbookingapp.controller;

import com.flightbooking.flightticketbookingapp.config.OpenApiConfiguration;
import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.payload.*;
import com.flightbooking.flightticketbookingapp.service.FlightService;
import com.flightbooking.flightticketbookingapp.service.PlaneService;
import com.flightbooking.flightticketbookingapp.service.UserService;
import com.flightbooking.flightticketbookingapp.user.User;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {
    private static final Logger logger= LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private FlightService flightService;
    @Autowired
    private PlaneService planeService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-flight")
    public ResponseEntity<String> createFlight(@Valid @RequestBody  CreateFlightPayload createPayload){
        logger.info("Endpoint '/add-flight' called by a user with ROLE_ADMIN");
        logger.debug("Create Flight Payload: {}", createPayload);
        String response=flightService.createFlight(createPayload);
        if(response.equals("Flight Added Successfully")){
            logger.info("Flight added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        else {
            logger.info("Failed to add flight: {}", response);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/view-all-flights")
    public ResponseEntity<List<Flight>> seeAllFlights(){
        logger.info("Endpoint '/view-all-flights' called by a user with ROLE_ADMIN");
        List<Flight> flights= flightService.listAllFlights();
        logger.debug("Retrieved {} flights", flights.size());
        return ResponseEntity.status(HttpStatus.OK).body(flights);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update-flight-details")
    public ResponseEntity<String> updateFlight(@RequestBody @Valid UpdateFlightPayload updateFlight) {
        logger.info("Endpoint '/update-flight-details' called by a user with ROLE_ADMIN");
        logger.debug("Update Flight Payload: {}", updateFlight);
        String response =flightService.updateFlight(updateFlight);
        if(response.equals("Flight details updated")) {
            logger.info("Flight details updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            logger.info("Flight details update failed: {}", response);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/change-flight-status")
    public ResponseEntity<String> changeFlightStatus( @RequestBody @Valid changeFlightStatusPayload changeFlightStatusPayload){
        logger.info("Endpoint '/change-flight-status' called by a user with ROLE_ADMIN");
        logger.debug("Change Flight Status Payload: {}", changeFlightStatusPayload);
        String response =flightService.setNewFlightStatus(changeFlightStatusPayload.getStatus(), changeFlightStatusPayload.getFlightId());
        if(response.equals("Status Changed Successfully")) {
            logger.info("Flight status changed successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            logger.info("Flight status change failed: {}", response);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-plane")
    public ResponseEntity<String> createPlane(@RequestBody @Valid CreatePlanePayload createPlanePayload){
        logger.info("Endpoint '/add-plane' called by a user with ROLE_ADMIN");
        logger.debug("Create Plane Payload: {}", createPlanePayload);
        planeService.addPlane(createPlanePayload);
        logger.info("New plane added successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).body("New Plane Added");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update-plane-details")
    public ResponseEntity<String> updatePlane(@RequestBody @Valid UpdatePlanePayload updatePlanePayload) {
        logger.info("Endpoint '/update-plane-details' called by a user with ROLE_ADMIN");
        logger.debug("Update Plane Payload: {}", updatePlanePayload);
        String response= planeService.updatePlane(updatePlanePayload);
        if(response.equals("Plane details updated")) {
            logger.info("Plane details updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            logger.info("Plane details update failed: {}", response);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/change-plane-status")
    public ResponseEntity<String> changePlaneStatus( @RequestBody @Valid ChangePlaneStatusPayload changePlaneStatusPayload){
        logger.info("Endpoint '/change-plane-status' called by a user with ROLE_ADMIN");
        logger.debug("Change Plane Status Payload: {}", changePlaneStatusPayload);
        String response =planeService.setNewPlaneStatus(changePlaneStatusPayload.getStatus(), changePlaneStatusPayload.getId());
        if(response.equals("Status Changed Successfully")) {
            logger.info("Plane status changed successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            logger.info("Plane status change failed: {}", response);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/view-all-users")
    public ResponseEntity<List<User>> seeAllUsers(){
        logger.info("Endpoint '/view-all-users' called by a user with ROLE_ADMIN");
        List<User> users= userService.listAllUsers();
        logger.debug("Retrieved {} users", users.size());
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update-user-details")
    public ResponseEntity<String> updateUser(@RequestBody @Valid UpdateUserPayload updateUserPayload) {
       logger.info("Endpoint '/update-user-details' called by a user with ROLE_ADMIN");
       logger.debug("Update User Payload: {}", updateUserPayload);
       String updateStatus=  userService.updateUser(updateUserPayload);
          if(updateStatus.equals("User Details Updated")) {
              logger.info("User details updated successfully");
              return ResponseEntity.status(HttpStatus.OK).body(updateStatus);
          }
          else {
              logger.info("User details update failed: {}", updateStatus);
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updateStatus);
          }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/block-user")
    public ResponseEntity<String> blockUser( @RequestBody @Valid BlockUserPayload blockUserPayload){
        logger.info("Endpoint '/block-user' called by a user with ROLE_ADMIN");
        logger.debug("Block User Payload: {}", blockUserPayload);
        String blockStatus =userService.blockUser(blockUserPayload.getStatus(), blockUserPayload.getId());
       if(blockStatus.equals("User Blocked Successfully"))
       {
           logger.info("User blocked successfully");
           return ResponseEntity.status(HttpStatus.OK).body(blockStatus);
       }
       else if (blockStatus.equals("User Id invalid")){
           logger.info("Invalid user ID provided");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(blockStatus);
       }
       else {
           logger.info("Failed to block user: {}", blockStatus);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(blockStatus);
       }
    }

}
