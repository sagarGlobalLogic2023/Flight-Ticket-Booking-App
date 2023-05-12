package com.flightbooking.flightticketbookingapp.service;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.payload.UpdateProfilePayload;
import com.flightbooking.flightticketbookingapp.payload.UpdateUserPayload;
import com.flightbooking.flightticketbookingapp.repository.UserRepo;
import com.flightbooking.flightticketbookingapp.user.Role;
import com.flightbooking.flightticketbookingapp.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Autowired
    private UserService userService;

    @Mock
    private UserRepo userRepo;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void updateUser() {

        List<Booking> bookings = new ArrayList<>();

        User oldUser = new User(1L, "Ashish", "Bansal", "ab@gmail.com", "123456", "No", bookings, Role.ROLE_USER);

        UpdateUserPayload updateUserPayload = new UpdateUserPayload(1L, "anoop@gmail.com", "54321", "Anoop", "Jalota", "No", Role.ROLE_USER);

        User newUser = new User();

        newUser.setUserId(updateUserPayload.getUserId());
        newUser.setEmail(updateUserPayload.getEmail());
        newUser.setPassword(updateUserPayload.getPassword());
        newUser.setFirstName(updateUserPayload.getFirstName());
        newUser.setLastName(updateUserPayload.getLastName());

        Mockito.when(userRepo.save(newUser)).thenReturn(newUser);
        assertEquals(oldUser, userService.updateUser(updateUserPayload));

    }


    @Test
    void listAllUsers() {

        List<Booking> b1 = new ArrayList<>();
        List<Booking> b2 = new ArrayList<>();

        List<User> users = new ArrayList<>();

        User u1 = new User(1L, "Mohit", "Sharma", "msgmail.com", "1234", "No", b1, Role.ROLE_USER);
        User u2 = new User(2L, "Raghav", "Sharma", "rs@gmail.com", "4321", "Yes", b2, Role.ROLE_USER);

        users.add(u1);
        users.add(u2);

        Mockito.when(userRepo.findAll()).thenReturn(users);
        assertEquals(users, userRepo.findAll());

    }


    @Test
    void blockUser() {



    }


    @Test
    void getUser() {

        Long userId = 2L;

        List<Booking> booking = new ArrayList<>();

        User user = new User(userId, "Aditya", "Maitreya", "adi@gmail.com", "1234", "No", booking, Role.ROLE_USER);

        Mockito.when(userRepo.findById(userId).get()).thenReturn(user);
        assertEquals(user, userRepo.findById(userId).get());

    }


}