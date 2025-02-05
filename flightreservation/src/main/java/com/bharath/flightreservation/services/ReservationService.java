package com.bharath.flightreservation.services;

import com.bharath.flightreservation.dtos.ReservationDTO;
import com.bharath.flightreservation.entities.Reservation;

public interface ReservationService {
    Reservation bookFlight(ReservationDTO reservationReqDTO);
}
