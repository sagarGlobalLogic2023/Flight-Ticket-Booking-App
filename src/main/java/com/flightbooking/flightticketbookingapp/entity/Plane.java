package com.flightbooking.flightticketbookingapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Plane")
@Table(name = "plane")
@NoArgsConstructor
@Data
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
            name = "plane_id",
            updatable = false
    )
    private Long planeId;
    @JsonIgnore
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
    @NotNull
    private Integer capacity;

    @Column(
            name = "airline",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull
    @NotEmpty
    @NotBlank
    private String airline;

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotNull
    @NotEmpty
    @NotBlank
    private String status;

    public Plane(Integer capacity, String airline, String status) {
        this.capacity = capacity;
        this.airline = airline;
        this.status = status;
    }
}
