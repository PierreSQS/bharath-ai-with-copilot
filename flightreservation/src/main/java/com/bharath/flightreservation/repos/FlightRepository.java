package com.bharath.flightreservation.repos;

import com.bharath.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("from Flight where departureCity=:from and arrivalCity=:to and dateOfDeparture=:departureDate")
    List<Flight> findFlights(@Param("from") String from, @Param("to") String to, @Param("departureDate") Date departureDate);

}