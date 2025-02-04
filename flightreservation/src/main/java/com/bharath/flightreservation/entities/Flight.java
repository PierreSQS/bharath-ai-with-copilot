package com.bharath.flightreservation.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "FLIGHT")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FLIGHT_NUMBER")
    private String flightNumber;

    @Column(name = "OPERATING_AIRLINES")
    private String operatingAirlines;

    @Column(name = "DEPARTURE_CITY")
    private String departureCity;

    @Column(name = "ARRIVAL_CITY")
    private String arrivalCity;

    @Column(name = "DATE_OF_DEPARTURE")
    private LocalDate dateOfDeparture;

    @Column(name = "ESTIMATED_DEPARTURE_TIME")
    private LocalDateTime estimatedDepartureTime;
}