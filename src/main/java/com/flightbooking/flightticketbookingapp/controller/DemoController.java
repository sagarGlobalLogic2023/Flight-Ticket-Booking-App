package com.flightbooking.flightticketbookingapp.controller;

import com.flightbooking.flightticketbookingapp.service.UserService;
import com.flightbooking.flightticketbookingapp.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/demo")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class DemoController {
    private final UserService userService;
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello from secured endpoint.");
    }

    @GetMapping("/view-all-users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> seeAllUsers(){
        List<User> users= userService.listAllUsers();
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

}
