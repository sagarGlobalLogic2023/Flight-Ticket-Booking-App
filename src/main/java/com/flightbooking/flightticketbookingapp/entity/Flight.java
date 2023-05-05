package com.flightbooking.flightticketbookingapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
            name = "flight_id",
            updatable = false
    )
    private Long flightId;

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

    @JsonIgnore
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "flight"
    )
    private List<Booking> bookings = new ArrayList<>();

    @Column(
            name = "departure",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime departure;

    @Column(
            name = "arrival",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime arrival;

    @Column(
            name = "duration"
    )
    private Long duration;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "plane_id",
            referencedColumnName = "plane_id",
            foreignKey = @ForeignKey(
                    name = "flight_user_fk"
            )
    )
    private Plane plane;

    @Column(
            name = "status",
            nullable = false
    )
    private String status;

    public Flight(String source, String destination, Integer availableSeats, Integer fare, LocalDateTime departure, LocalDateTime arrival, Long duration, Plane plane, String status) {
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.fare = fare;
        this.departure = departure;
        this.arrival = arrival;
        this.duration = duration;
        this.plane = plane;
        this.status = status;
    }
}
