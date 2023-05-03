package com.flightbooking.flightticketbookingapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Plane")
@Table(name = "plane")
public class Plane {
    @Id
    @SequenceGenerator(
            name = "plane_sequence",
            sequenceName = "plane_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "plane_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @OneToMany(
            mappedBy = "plane",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Flight> flights = new ArrayList<>();

    @Column(
            name = "capacity",
            nullable = false
    )
    private Integer capacity;

    @Column(
            name = "airline",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String airline;

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String status;
}
