package com.flightbooking.flightticketbookingapp.payload;

import com.flightbooking.flightticketbookingapp.user.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class UpdateUserPayload {

    @NotNull
    private Long userId;
    @Email
    @NotNull
    @NotEmpty
    @NotBlank
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")
    @NotNull
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
    @NotNull
    @NotEmpty
    @NotBlank
    private String isBlocked;
    @NotNull
    @NotEmpty
    @NotBlank
    private String role;
}
