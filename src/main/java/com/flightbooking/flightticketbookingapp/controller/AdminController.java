package com.flightbooking.flightticketbookingapp.controller;

import com.flightbooking.flightticketbookingapp.entity.Flight;
import com.flightbooking.flightticketbookingapp.entity.Plane;
import com.flightbooking.flightticketbookingapp.entity.User;
import com.flightbooking.flightticketbookingapp.payload.*;
import com.flightbooking.flightticketbookingapp.service.FlightService;
import com.flightbooking.flightticketbookingapp.service.PlaneService;
import com.flightbooking.flightticketbookingapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private PlaneService planeService;
//    public AdminController(FlightService flightService) {
//        this.flightService = flightService;
//    }

    @Autowired
    private UserService userService;

    @PostMapping("/add-flight")
    public ResponseEntity<String> createFlight(@RequestBody @Valid CreateFlightPayload createPayload){
        flightService.createFlight(createPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body("Flight Added Successfully");
    }

    @GetMapping("/view-all-flights")
    public ResponseEntity<List<Flight>> seeAllFlights(){
        List<Flight> flights= flightService.listAllFlights();
        return ResponseEntity.status(HttpStatus.CREATED).body(flights);
    }

    @PutMapping("/update-flight-details")
    public ResponseEntity<String> updateFlight(@RequestBody @Valid UpdateFlightPayload updateFlight) {
        flightService.updateFlight(updateFlight);
        return ResponseEntity.status(HttpStatus.CREATED).body("Flight details updated");
    }
    @PutMapping("/change-flight-status")
    public ResponseEntity<String> changeFlightStatus( String status, Long id){
        flightService.setNewFlightStatus(status, id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Status Changed Successfully");
    }
    @PostMapping("/add-plane")
    public ResponseEntity<String> createPlane(@RequestBody @Valid CreatePlanePayload createPlanePayload){
        planeService.addPlane(createPlanePayload);
        return  ResponseEntity.status(HttpStatus.CREATED).body("New Plane Added");
    }
    @PutMapping("/update-plane-details")
    public ResponseEntity<String> updatePlane(@RequestBody @Valid UpdatePlanePayload updatePlanePayload) {
        planeService.updatePlane(updatePlanePayload);
        return ResponseEntity.status(HttpStatus.CREATED).body("Plane details updated");
    }
    @PutMapping("/change-plane-status")
    public ResponseEntity<String> changePlaneStatus( String status, Long id){
        planeService.setNewPlaneStatus(status, id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Status Changed Successfully");
    }

    @GetMapping("/view-all-users")
    public ResponseEntity<List<User>> seeAllUsers(){
        List<User> users= userService.listAllUsers();
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @PutMapping("/update-user-details")
    public ResponseEntity<String> updateUser(@RequestBody @Valid UpdateUserPayload updateUserPayload) {
          String updateStatus=  userService.updateUser(updateUserPayload);
          if(updateStatus.equals("User details updated")) {
              return ResponseEntity.status(HttpStatus.CREATED).body(updateStatus);
          }
          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(updateStatus);
    }

    @PutMapping("/block-user")
    public ResponseEntity<String> blockUser( String status, Long id){
       String blockStatus =userService.blockUser(status, id);
       if(blockStatus.equals("User Blocked Sucessfully"))
       {
           return ResponseEntity.status(HttpStatus.CREATED).body("Status Changed Successfully");
       }
       else if (blockStatus.equals("User Id invalid")){
           return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User ID invalid");
       }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(blockStatus);

    }



}
