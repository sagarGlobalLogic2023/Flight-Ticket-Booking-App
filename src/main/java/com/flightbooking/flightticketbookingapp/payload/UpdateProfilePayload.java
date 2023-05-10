package com.flightbooking.flightticketbookingapp.payload;

import lombok.Data;

@Data
public class UpdateProfilePayload {

    private Long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
