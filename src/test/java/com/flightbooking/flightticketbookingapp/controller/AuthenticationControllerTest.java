package com.flightbooking.flightticketbookingapp.controller;

import com.flightbooking.flightticketbookingapp.auth.AuthenticationRequest;
import com.flightbooking.flightticketbookingapp.auth.AuthenticationResponse;
import com.flightbooking.flightticketbookingapp.auth.RegisterRequest;
import com.flightbooking.flightticketbookingapp.service.AuthenticationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class AuthenticationControllerTest {

    @Autowired
    private AuthenticationController authenticationController;
    @MockBean
    private AuthenticationService authenticationService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        RegisterRequest request= new RegisterRequest();
        request.setFirstname("Raghav");
        request.setLastname("Sharma");
        request.setEmail("raghavsharma2701@gmail.com");
        request.setPassword("123456");
        Mockito.when(authenticationService.register(request)).thenAnswer(invocation -> {
            ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.OK)
                    .body(AuthenticationResponse.builder()
                            .token("token")
                            .build());
            return responseEntity;
        });
        assertEquals(authenticationController.register(request),ResponseEntity.ok(ResponseEntity.status(HttpStatus.OK)
                .body(AuthenticationResponse.builder()
                        .token("token")
                        .build())));
    }

    @Test
    void testRegister() {
        AuthenticationRequest authenticationRequest= new AuthenticationRequest();
        authenticationRequest.setEmail("raghavsharma2701@gmail.com");
        authenticationRequest.setPassword("123456");
        Mockito.when(authenticationService.authenticate(authenticationRequest)).thenReturn(AuthenticationResponse.builder()
                .token("token")
                .build());
        assertEquals(authenticationController.register(authenticationRequest),ResponseEntity.ok(AuthenticationResponse.builder()
                .token("token")
                .build()));

    }
}