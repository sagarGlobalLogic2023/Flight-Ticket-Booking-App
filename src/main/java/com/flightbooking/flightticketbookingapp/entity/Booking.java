package com.flightbooking.flightticketbookingapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Booking")
@Table(name = "booking")
public class Booking {
    @EmbeddedId
    private BookingId id;
    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(
                    name = "booking_user_id_fk"
            )
    )
    private User user;
    @JsonIgnore
    @ManyToOne
    @MapsId("flightId")
    @JoinColumn(
            name = "flight_id",
            foreignKey = @ForeignKey(
                    name = "booking_flight_id_fk"
            )
    )
    private Flight flight;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt;

    @Column(
            name = "seat_number",
            nullable = false
    )
    private Integer seatNumber;

    @Column(
            name = "booking_status",
            nullable = false
    )
    private String status;
}
