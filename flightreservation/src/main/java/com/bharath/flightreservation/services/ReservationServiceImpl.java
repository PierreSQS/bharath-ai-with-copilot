package com.bharath.flightreservation.services;

import com.bharath.flightreservation.dtos.ReservationDTO;
import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.entities.Passenger;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repositories.FlightRepository;
import com.bharath.flightreservation.repositories.PassengerRepository;
import com.bharath.flightreservation.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final FlightRepository flightRepository;

    private final PassengerRepository passengerRepository;

    private final ReservationRepository reservationRepository;

    @Override
    public Reservation bookFlight(ReservationDTO reservationReqDTO) {
        // find the flight by requested flight id
        Flight foundFlight = flightRepository.findById(reservationReqDTO.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        // create a new Passenger object set the field
        // with RequestDTO and save it to the database
        Passenger savedPassenger = passengerRepository.save(Passenger.builder()
                .firstName(reservationReqDTO.getFirstName())
                .lastName(reservationReqDTO.getLastName())
                .phone(reservationReqDTO.getPhone())
                .email(reservationReqDTO.getEmail())
                .build());

        // create a Reservation object, set the savedPassenger and foundFlight
        // with the saved passenger object and save it to the database
        return reservationRepository.save(Reservation.builder()
                .passenger(savedPassenger)
                .flight(foundFlight)
                .build());
    }
}
