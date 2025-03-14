package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.repositories.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = {FlightController.class})
class FlightControllerTest {

    @MockitoBean
    FlightRepository flightRepository;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        Mockito.reset(flightRepository);
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

    @Test
    void findFlightsReturnsDisplayFlightsViewWithFlights() throws Exception {
        // Given
        Flight flight = new Flight();
        flight.setDepartureCity("NYC");
        flight.setArrivalCity("LAX");
        flight.setDateOfDeparture(LocalDate.of(2023, 12, 25));
        given(flightRepository
                        .findByDepartureCityAndArrivalCityAndDateOfDeparture("NYC", "LAX",
                                LocalDate.of(2023, 12, 25)))
                .willReturn(Collections.singletonList(flight));

        // When, Then
        MvcResult mvcResult = mockMvc.perform(post("/findFlights")
                        .param("to", "LAX")
                        .param("from", "NYC")
                        .param("departureDate", "2023-12-25"))
                .andExpect(status().isOk())
                .andExpect(view().name("displayFlights"))
                .andExpect(model().attribute("flights", hasSize(1)))
                .andExpect(model().attribute("flights", equalTo(List.of(flight))))
                .andExpect(content().string(containsString("showCompleteReservation?flightId")))
                .andDo(print())
                .andReturn();


        ModelAndView modelAndView = mvcResult.getModelAndView();
        List<Flight> flights = (List<Flight>) modelAndView.getModel().get("flights");
        Flight extractedFlight = flights.getFirst();
        assertThat(extractedFlight.getDepartureCity()).isEqualTo("NYC");
        assertThat(extractedFlight.getArrivalCity()).isEqualTo("LAX");
    }

    @Test
    void findFlightsReturnsDisplayFlightsViewWithNoFlights() throws Exception {
        given(flightRepository.findByDepartureCityAndArrivalCityAndDateOfDeparture(
                "NYC", "LAX", LocalDate.of(2023, 12, 25)))
                .willReturn(Collections.emptyList());

        mockMvc.perform(post("/findFlights")
                        .param("to", "LAX")
                        .param("from", "NYC")
                        .param("departureDate", "2023-12-25"))
                .andExpect(status().isOk())
                .andExpect(view().name("displayFlights"))
                .andExpect(model().attribute("flights", hasSize(0)))
                .andExpect(content().string(not(
                        containsString("showCompleteReservation?flightId"))))
                .andDo(print());
    }

}