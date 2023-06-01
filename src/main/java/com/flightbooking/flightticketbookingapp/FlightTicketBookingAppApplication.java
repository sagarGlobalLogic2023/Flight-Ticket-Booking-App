package com.flightbooking.flightticketbookingapp;

import com.flightbooking.flightticketbookingapp.repository.UserRepo;
import com.flightbooking.flightticketbookingapp.security.JwtService;
import com.flightbooking.flightticketbookingapp.user.Role;
import com.flightbooking.flightticketbookingapp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class FlightTicketBookingAppApplication implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(FlightTicketBookingAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepo.findByEmail("vaibhav@gmail.com")==null) {
            User user = User.builder()
                    .firstName("Vaibhav")
                    .lastName("Gupta")
                    .email("vaibhav@gmail.com")
                    .password(passwordEncoder.encode("Vaibhav@123"))
                    .isBlocked("No")
                    .role(Role.ROLE_ADMIN)
                    .build();
            userRepo.save(user);
        }
    }
}
