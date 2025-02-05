package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.repositories.FlightRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FlightRepository flightRepository;

    @Test
    void testDisplayReservationForm() throws Exception {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setDepartureCity("NYC");
        flight.setArrivalCity("LAX");

        Mockito.when(flightRepository.findById(anyLong())).thenReturn(Optional.of(flight));

        MvcResult result = mockMvc.perform(get("/displayReservationForm")
                        .param("flightID", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("completeReservation"))
                .andExpect(model().attributeExists("flight"))
                .andExpect(model().attribute("flight", is(flight)))
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        Flight modelFlight = (Flight) modelAndView.getModel().get("flight");
        assertEquals("NYC", modelFlight.getDepartureCity());
        assertEquals("LAX", modelFlight.getArrivalCity());
    }
}