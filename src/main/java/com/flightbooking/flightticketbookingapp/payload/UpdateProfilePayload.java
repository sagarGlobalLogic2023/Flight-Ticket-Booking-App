package com.flightbooking.flightticketbookingapp.payload;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateProfilePayload {

    @NotNull
    private Long userId;
    @Email
    @NotNull
    @NotBlank
    @NotEmpty
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")
    @NotEmpty
    @NotBlank
    private String password;
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;
    @NotNull
    @NotEmpty
    @NotBlank
    private String lastName;

}
