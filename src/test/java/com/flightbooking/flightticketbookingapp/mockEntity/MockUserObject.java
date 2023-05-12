package com.flightbooking.flightticketbookingapp.mockEntity;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import com.flightbooking.flightticketbookingapp.user.Role;
import com.flightbooking.flightticketbookingapp.user.User;
import com.flightbooking.flightticketbookingapp.request.UserRequest;
import com.flightbooking.flightticketbookingapp.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class MockUserObject {

    public static UserRequest getUserRequest() {


        List<Booking> bookings = new ArrayList<>();
        UserRequest userRequest = new UserRequest();

        userRequest.setUserId(1L);
        userRequest.setFirstName("Ajit");
        userRequest.setLastName("Doval");
        userRequest.setEmail("ad@gov.in");
        userRequest.setPassword("1234");
        userRequest.setIsBlocked("No");
        userRequest.setRole("Admin");
     //   userRequest.setBookings(bookings);

        return userRequest;

    }

    public static User getUser() {

        List<Booking> bookings = new ArrayList<>();
        User user = new User();

        user.setUserId(1L);
        user.setFirstName("Ajit");
        user.setLastName("Doval");
        user.setEmail("ad@gov.in");
        user.setPassword("1234");
        user.setIsBlocked("No");
        //user.setRole("Admin");
        user.setRole(Role.ROLE_ADMIN);
        user.setBookings(bookings);

        return user;

    }

    public static UserResponse getUserResponse() {


        UserResponse userResponse = new UserResponse();

        userResponse.setUserId(getUser().getUserId());
        userResponse.setFirstName(getUser().getFirstName());
        userResponse.setLastName(getUser().getLastName());
        userResponse.setEmail(getUser().getEmail());
        userResponse.setPassword(getUser().getPassword());
        userResponse.setIsBlocked(getUser().getIsBlocked());
        //userResponse.setRole(getUser().getRole());
        userResponse.setRole(getUser().getRole());
        //userResponse.setBookings(getUser().getBookings());

        return userResponse;

    }


}