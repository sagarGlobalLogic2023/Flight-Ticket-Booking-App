package com.flightbooking.flightticketbookingapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "flight_id")
    private Long flightId;

    @Column(
            name = "time",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    @NotNull
    private LocalDateTime createdAt;
}
