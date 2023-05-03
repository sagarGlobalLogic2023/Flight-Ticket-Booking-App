package com.flightbooking.flightticketbookingapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Flight")
@Table(name = "flight")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @SequenceGenerator(
            name = "flight_sequence",
            sequenceName = "flight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "flight_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "source",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String source;

    @Column(
            name = "destination",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String destination;

    @Column(
            name = "available_seats",
            nullable = false

    )
    private Integer availableSeats;

    @Column(
            name = "fare",
            nullable = false

    )
    private Integer fare;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "flight"
    )
    private List<Booking> bookings = new ArrayList<>();

    @Column(
            name = "departure_time",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime departureTime;

    @Column(
            name = "arrival_time",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime arrivalTime;

    @Column(
            name = "duration",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime duration;


}
