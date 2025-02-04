package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.repositories.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = {FlightController.class})
class FlightControllerTest {

    @MockitoBean
    private FlightRepository flightRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void displayFindFlightReturnsFindFlightsView() throws Exception {
        mockMvc.perform(get("/findFlights"))
                .andExpect(status().isOk())
                .andExpect(view().name("findFlights"))
                .andExpect(content()
                        .string(containsString("<title>Find Flights</title>")))
                .andDo(print());
    }

}