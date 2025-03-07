package com.bharath.flightreservation.repos;

import com.bharath.flightreservation.entities.Passenger;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}