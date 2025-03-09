package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.dtos.ReservationDTO;
import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repositories.FlightRepository;
import com.bharath.flightreservation.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @MockitoBean
    ReservationService reservationServ;

    @Captor
    private ArgumentCaptor<ReservationDTO> reservationDTOArgumentCaptor;

    Flight flightMock;

    Reservation reservationMock;

    @BeforeEach
    void setUp() {
        flightMock = new Flight();
        flightMock.setId(1L);
        flightMock.setDepartureCity("NYC");
        flightMock.setArrivalCity("LAX");

        reservationMock = new Reservation();
        reservationMock.setId(1L);
    }

    @Test
    void testDisplayReservationForm() throws Exception {
        // Given
        given(flightRepository.findById(anyLong()))
                .willReturn(Optional.of(flightMock));

        // When, Then
        MvcResult result = mockMvc.perform(get("/showCompleteReservation")
                        .param("flightId", "1"))
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
        assertThat(modelFlight.getDepartureCity()).isEqualTo("NYC");
        assertThat(modelFlight.getArrivalCity()).isEqualTo("LAX");
    }

    @Test
    void testDisplayReservationForm_FlightNotFound() {
        assertThatThrownBy(() ->
                mockMvc.perform(get("/showCompleteReservation")
                        .param("flightId", "1")))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Flight not found");
    }

    @Test
    void testCompleteReservation() throws Exception {
        // Given
        given(reservationServ.bookFlight(reservationDTOArgumentCaptor.capture()))
                .willReturn(reservationMock);

        // When, Then
        mockMvc.perform(post("/completeReservation")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("phone", "1234567890")
                        .param("email", "john.doe@example.com")
                        .param("flightId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("reservationConfirmation"))
                .andExpect(model().attribute("msg", "Reservation created successfully and the id is 1"))
                .andExpect(content().string(containsString("Reservation created successfully and the id is 1")))
                .andDo(print());

        // Verify
        assertThat(reservationDTOArgumentCaptor.getValue().getFirstName()).isEqualTo("John");
        assertThat(reservationDTOArgumentCaptor.getValue().getLastName()).isEqualTo("Doe");
        assertThat(reservationDTOArgumentCaptor.getValue().getPhone()).isEqualTo("1234567890");
        assertThat(reservationDTOArgumentCaptor.getValue().getEmail()).isEqualTo("john.doe@example.com");
        assertThat(reservationDTOArgumentCaptor.getValue().getFlightId()).isEqualTo(1L);
    }

    @Test
    void testCompleteReservation_FlightNotFound() {
        // Given
        given(reservationServ.bookFlight(any(ReservationDTO.class)))
                .willThrow(new RuntimeException("Flight not found"));

        // When, Then
        assertThatThrownBy(() -> {
            mockMvc.perform(post("/completeReservation")
                .param("flightId", "999")); // Non-existent flight ID

    }).isInstanceOf(Exception.class)
            .hasMessageContaining("Flight not found");
    }

}