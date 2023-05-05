package com.flightbooking.flightticketbookingapp.payload;

import lombok.Data;


@Data
public class UpdateUserPayload {

    private Long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String isBlocked;
    private  String role;
}
