package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.auth.AuthenticationRequest;
import com.flightbooking.flightticketbookingapp.auth.AuthenticationResponse;
import com.flightbooking.flightticketbookingapp.auth.RegisterRequest;
import com.flightbooking.flightticketbookingapp.repository.UserRepository;
import com.flightbooking.flightticketbookingapp.security.JwtService;
import com.flightbooking.flightticketbookingapp.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class AuthenticationServiceTest {
@Autowired
private AuthenticationService authenticationService;
@MockBean
private UserRepository userRepository;
@MockBean
private PasswordEncoder passwordEncoder;
@MockBean
private JwtService jwtService;
@MockBean
private AuthenticationManager authenticationManager;

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
        request.setPassword("12345");
        request.setEmail("raghav@gmail.com");
        User user= new User();
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode(request.getPassword())).thenReturn(request.getPassword());
        Mockito.when(jwtService.generateToken(Mockito.any(User.class))).thenReturn("token");
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(AuthenticationResponse.builder()
                .token("token")
                .build()),authenticationService.register(request));
    }
    @Test
    void invalidRegister() {
        RegisterRequest request= new RegisterRequest();
        request.setFirstname("Raghav");
        request.setLastname("Sharma");
        request.setPassword("12345");
        request.setEmail("raghav@gmail.com");
        User user= new User();
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("Email already exists!"),authenticationService.register(request));
    }
    @Test
    void authenticate() {
        AuthenticationRequest request= new AuthenticationRequest();
        request.setEmail("raghav@gmail.com");
        request.setPassword("12345");
        User user= new User();
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        Mockito.when(jwtService.generateToken(user)).thenReturn("token");
        assertEquals( AuthenticationResponse.builder()
                .token("token")
                .build(),authenticationService.authenticate(request));
        Mockito.verify(authenticationManager,Mockito.times(1)).authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
    }
}