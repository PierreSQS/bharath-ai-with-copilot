package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.repositories.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    FlightRepository flightRepository;

    Flight flightMock;

    @BeforeEach
    void setUp() {
        flightMock = new Flight();
        flightMock.setId(1L);
        flightMock.setDepartureCity("NYC");
        flightMock.setArrivalCity("LAX");
    }

    @Test
    void testDisplayReservationForm() throws Exception {
        // Given
        given(flightRepository.findById(anyLong()))
                .willReturn(Optional.of(flightMock));

        // When, Then
        MvcResult result = mockMvc.perform(get("/displayReservationForm")
                        .param("flightID", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("completeReservation"))
                .andExpect(model().attribute("flight", is(flightMock)))
                .andExpect(content()
                        .string(containsString("<title>Complete Reservation</title>")))
                .andDo(print())
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();
        assert modelAndView != null;
        Flight modelFlight = (Flight) modelAndView.getModel().get("flight");
        assertEquals("NYC", modelFlight.getDepartureCity());
        assertEquals("LAX", modelFlight.getArrivalCity());
    }
}