package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ReservationController {

    private final FlightRepository flightRepository;

    @GetMapping("/showCompleteReservation")
    public String displayReservationForm(@RequestParam("flightId") Long flightID, Model model) {
        Flight flight = flightRepository.findById(flightID)
                .orElseThrow(() ->  new RuntimeException("Flight not found"));
        model.addAttribute("flight", flight);
        return "completeReservation";
    }
}