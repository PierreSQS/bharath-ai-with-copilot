package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Controller

public class FlightController {

    private final FlightRepository flightRepository;

    @GetMapping("/findFlights")
    public String displayFindFlightForm() {
        return "findFlights";
    }

    @PostMapping("/findFlights")
    public String findFlights(@RequestParam("to") String to,
                              @RequestParam("from") String from,
                              @RequestParam("departureDate") LocalDate departureDate,
                              Model model) {
        List<Flight> flights = flightRepository
                .findByDepartureCityAndArrivalCityAndDateOfDeparture(from, to, departureDate.atStartOfDay());
        model.addAttribute("flights", flights);
        return "displayFlights";
    }

}