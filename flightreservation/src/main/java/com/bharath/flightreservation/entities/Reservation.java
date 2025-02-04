package com.bharath.flightreservation.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CHECKED_IN")
    private Boolean checkedIn;

    @Column(name = "NUMBER_OF_BAGS")
    private int numberOfBags;

    @OneToOne
    @JoinColumn(name = "PASSENGER_ID", nullable = false)
    private Passenger passenger;

    @OneToOne
    @JoinColumn(name = "FLIGHT_ID", nullable = false)
    private Flight flight;

    @CreationTimestamp
    @Column(name = "CREATED", nullable = false, updatable = false, insertable = false)
    private LocalDateTime created;

}