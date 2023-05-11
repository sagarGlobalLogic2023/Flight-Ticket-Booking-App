package com.flightbooking.flightticketbookingapp.request;

import com.flightbooking.flightticketbookingapp.entity.Booking;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRequest {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String isBlocked;
    private String role;

}