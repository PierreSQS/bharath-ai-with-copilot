package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.dtos.ReservationDTO;
import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repositories.FlightRepository;
import com.bharath.flightreservation.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ReservationController {

    private final FlightRepository flightRepository;

    private final ReservationService reservationService;

    @GetMapping("/showCompleteReservation")
    public String displayReservationForm(@RequestParam("flightId") Long flightID, Model model) {
        Flight flight = flightRepository.findById(flightID)
                .orElseThrow(() ->  new RuntimeException("Flight not found"));
        model.addAttribute("flight", flight);
        return "completeReservation";
    }

    @PostMapping("/completeReservation")
    public String completeReservation(ReservationDTO reservationReqDTO, Model model) {
        Reservation reservation = reservationService.bookFlight(reservationReqDTO);
        model.addAttribute("msg",
                "Reservation created successfully and the id is " + reservation.getId());
        return "reservationConfirmation";
    }
}