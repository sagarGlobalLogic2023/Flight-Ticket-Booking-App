package com.flightbooking.flightticketbookingapp.entity;

import com.flightbooking.flightticketbookingapp.user.User;
import jakarta.persistence.*;
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

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(
                    name = "booking_user_id_fk"
            )
    )
    private User user;

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
            name = "status"
    )
    private Integer status;
}
