package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.dtos.ReservationUpdateDTO;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    private final ReservationRepository reservationRepository;

    @GetMapping("/{flightID}")
    public ResponseEntity<Reservation> findReservation(@PathVariable Long flightID) {
        return reservationRepository.findById(flightID)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestBody ReservationUpdateDTO reservationUpdateDTO) {
        return reservationRepository.findById(reservationUpdateDTO.getId())
                .map(reservation -> {
                    reservation.setCheckedIn(reservationUpdateDTO.isCheckedIn());
                    reservation.setNumberOfBags(reservationUpdateDTO.getNumberOfBags());
                    Reservation updatedReservation = reservationRepository.save(reservation);
                    return ResponseEntity.ok(updatedReservation);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}