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
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {


    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;


//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }


    @Test
    void updateUser() {

        List<Booking> bookings = new ArrayList<>();

        User oldUser = new User(1L, "Ashish", "Bansal", "ab@gmail.com", "123456", "No", bookings, Role.ROLE_USER);

        UpdateUserPayload updateUserPayload = new UpdateUserPayload(1L, "anoop@gmail.com", "54321", "Anoop", "Jalota", "No", "ROLE_USER");

        User newUser = new User();

        newUser.setUserId(updateUserPayload.getUserId());
        newUser.setEmail(updateUserPayload.getEmail());
        newUser.setPassword(updateUserPayload.getPassword());
        newUser.setFirstName(updateUserPayload.getFirstName());
        newUser.setLastName(updateUserPayload.getLastName());
        newUser.setIsBlocked(updateUserPayload.getIsBlocked());
        newUser.setRole(Role.valueOf(updateUserPayload.getRole()));
        newUser.setBookings(bookings);
        Mockito.when(userRepo.findById(updateUserPayload.getUserId())).thenReturn(Optional.of(oldUser));
        Mockito.when(userRepo.save(newUser)).thenReturn(newUser);
        assertEquals("User Details Updated",userService.updateUser(updateUserPayload));
    }
    @Test
    void invalidUpdateUser() {

        List<Booking> bookings = new ArrayList<>();
        User oldUser = new User(1L, "Ashish", "Bansal", "ab@gmail.com", "123456", "No", bookings, Role.ROLE_USER);
        UpdateUserPayload updateUserPayload = new UpdateUserPayload(1L, "anoop@gmail.com", "54321", "Anoop", "Jalota", "No", "ROLE_USER");
        Mockito.when(userRepo.findById(updateUserPayload.getUserId())).thenReturn(Optional.empty());
        assertEquals("User Not Found",userService.updateUser(updateUserPayload));
    }
    @Test
    void listAllUsers() {

        List<User> mockUsers = new ArrayList<>();
        List<Booking>bookings= new ArrayList<>();

        mockUsers.add(new User(1L, "Mohit", "Sharma", "mohit@gl.com", "1234", "No", bookings,Role.ROLE_USER));
        mockUsers.add(new User(2L, "Devansh", "Shukla", "dev@gl.com", "4321", "No", bookings,Role.ROLE_USER));

        Mockito.when(userRepo.findAll()).thenReturn(mockUsers);

        List<User> result = userService.listAllUsers();

        // Verify that the userRepo.findAll method was called
        Mockito.verify(userRepo).findAll();

        // Verify that the returned list of users matches the mockUsers list
        assertEquals(mockUsers, result);

    }

    @Test
    void blockUser() {
        Long userId = 1L;
        String isBlocked = "Yes";

        User oldMockUser = new User();
        oldMockUser.setUserId(userId);
        oldMockUser.setRole(Role.ROLE_USER);

        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.of(oldMockUser));

        String result = userService.blockUser(isBlocked, userId);

        // Verify that the userRepo.changeUserStatus method was called
        Mockito.verify(userRepo).changeUserStatus(isBlocked, userId);

        assertEquals("User Blocked Successfully", result);
    }
    @Test
    void invalidBlockUser() {
        Long userId = 1L;
        String isBlocked = "Yes";

        User oldMockUser = new User();
        oldMockUser.setUserId(userId);
        oldMockUser.setRole(Role.ROLE_ADMIN);

        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.of(oldMockUser));

        String result = userService.blockUser(isBlocked, userId);

        assertEquals("User Id invalid", result);
    }
    @Test
    void invalid2BlockUser() {
        Long userId = 1L;
        String isBlocked = "Yes";
        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.empty());

        String result = userService.blockUser(isBlocked, userId);
        assertEquals("User does not Exist", result);
    }
    @Test
    void getUser() {

        Long userId = 2L;

        List<Booking> booking = new ArrayList<>();

        User user = new User(userId, "Aditya", "Maitreya", "adi@gmail.com", "1234", "No", booking, Role.ROLE_USER);

        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        assertEquals(user, userService.getUser(userId));
    }


    @Test
    void updateProfile() {

        UpdateProfilePayload updateProfilePayload = new UpdateProfilePayload(1L, "mohit@gl.com", "1234", "Mohit", "Sharma");

        List<Booking>bookings= new ArrayList<>();
        User oldMockUser = new User(1L, "Raghav", "Sharma", "raghav@gl.com", "4321", "No", bookings,Role.ROLE_USER);

        Mockito.when(userRepo.findById(updateProfilePayload.getUserId())).thenReturn(Optional.of(oldMockUser));
        User newUser = new User();

        newUser.setUserId(updateProfilePayload.getUserId());
        newUser.setEmail(updateProfilePayload.getEmail());
        newUser.setPassword(updateProfilePayload.getPassword());
        newUser.setFirstName(updateProfilePayload.getFirstName());
        newUser.setLastName(updateProfilePayload.getLastName());
        newUser.setIsBlocked(oldMockUser.getIsBlocked());
        newUser.setRole(oldMockUser.getRole());
        newUser.setBookings(bookings);
        Mockito.when(userRepo.save(newUser)).thenReturn(newUser);
        assertEquals("Profile updated successfully",userService.updateProfile(updateProfilePayload));

    }
    @Test
    void invalidUpdateProfile() {

        UpdateProfilePayload updateProfilePayload = new UpdateProfilePayload(1L, "mohit@gl.com", "1234", "Mohit", "Sharma");

        List<Booking>bookings= new ArrayList<>();
        Mockito.when(userRepo.findById(updateProfilePayload.getUserId())).thenReturn(Optional.empty());

        Mockito.verify(userRepo,Mockito.times(1)).save(Mockito.any(User.class));
        assertEquals("Error Updating Profile, user not found",userService.updateProfile(updateProfilePayload));
    }
}