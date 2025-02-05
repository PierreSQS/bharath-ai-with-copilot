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

    @GetMapping("/displayReservationForm")
    public String displayReservationForm(@RequestParam("flightID") Long flightID, Model model) {
        Flight flight = flightRepository.findById(flightID).orElse(null);
        model.addAttribute("flight", flight);
        return "completeReservation";
    }
}