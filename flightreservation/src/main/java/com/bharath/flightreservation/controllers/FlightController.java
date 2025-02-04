package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
// TODO: eventually remove the annotation below
// @RequestMapping("/flights")
public class FlightController {

    private final FlightRepository flightRepository;

    @GetMapping("/findFlights")
    public String displayFindFlight() {
        return "findFlights";
    }

}