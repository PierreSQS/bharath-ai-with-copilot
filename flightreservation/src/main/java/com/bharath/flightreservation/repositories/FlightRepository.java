package com.bharath.flightreservation.repositories;

import com.bharath.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureCityAndArrivalCityAndDateOfDeparture(String from, String to, LocalDate departureDate);
}