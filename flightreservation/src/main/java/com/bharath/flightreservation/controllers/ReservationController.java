package com.bharath.flightreservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.dtos.ReservationRequest;
import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repos.FlightRepository;
import com.bharath.flightreservation.services.ReservationService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, Model model) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new IllegalArgumentException("Invalid flight Id:" + flightId));
        model.addAttribute("flight", flight);
        return "completeReservation";
    }

    @PostMapping("/completeReservation")    
    public String completeReservation(ReservationRequest request,Model model) {

        Reservation reservation = reservationService.bookFlight(request);
        model.addAttribute("msg", "Reservation created successfully with a reservation id: "+reservation.getId());

        return "reservationConfirmation";
    }

}