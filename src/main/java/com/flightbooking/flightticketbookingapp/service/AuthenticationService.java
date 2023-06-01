package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.auth.AuthenticationRequest;
import com.flightbooking.flightticketbookingapp.auth.AuthenticationResponse;
import com.flightbooking.flightticketbookingapp.auth.RegisterRequest;
import com.flightbooking.flightticketbookingapp.repository.UserRepository;
import com.flightbooking.flightticketbookingapp.security.JwtService;
import com.flightbooking.flightticketbookingapp.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.flightbooking.flightticketbookingapp.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
            var user = User.builder()
                    .firstName(request.getFirstname())
                    .lastName(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ROLE_USER)
                    .isBlocked("No")
                    .build();

            userRepository.save(user);
            var jwt = jwtService.generateToken(user);
            return ResponseEntity.status(HttpStatus.OK).body(AuthenticationResponse.builder()
                    .token(jwt)
                    .build());
        }
        else return ResponseEntity.status(HttpStatus.OK).body("Email already exists!");
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        ); // user is authenticated

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
            var jwt = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwt)
                    .build();
        }
    }

