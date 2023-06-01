package com.flightbooking.flightticketbookingapp.controller;

import com.flightbooking.flightticketbookingapp.auth.AuthenticationRequest;
import com.flightbooking.flightticketbookingapp.auth.AuthenticationResponse;
import com.flightbooking.flightticketbookingapp.service.AuthenticationService;
import com.flightbooking.flightticketbookingapp.auth.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private static final Logger logger= LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody  RegisterRequest request
    ) {
        logger.info("Endpoint '/register' called");

        // Log the request payload if needed
        logger.debug("Register Request: {}", request);

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody  AuthenticationRequest request
    ) {
        logger.info("Endpoint '/authenticate' called");

        // Log the request payload if needed
        logger.debug("Authentication Request: {}", request);
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
