package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repositories.ReservationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ReservationRestController.class)
class ReservationRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ReservationRepository reservationRepository;

    @Autowired
    ObjectMapper objectMapper;

    Reservation reservationMock;

    @BeforeEach
    void setUp() {
        reservationMock = new Reservation();
        reservationMock.setId(1L);
        reservationMock.setCheckedIn(true);
        reservationMock.setNumberOfBags(2);
    }

    @Test
    void findReservation_ReturnsReservation_WhenFlightIDExists() throws Exception {
        given(reservationRepository.findById(anyLong())).willReturn(Optional.of(reservationMock));

        mockMvc.perform(get("/api/reservations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.checkedIn").value(true))
                .andExpect(jsonPath("$.numberOfBags").value(2))
                .andDo(print());
    }

    @Test
    void findReservation_ReturnsNotFound_WhenFlightIDDoesNotExist() {
        given(reservationRepository.findById(anyLong()))
                .willThrow(new RuntimeException("Flight ID not found"));

        assertThatThrownBy(() -> mockMvc.perform(get("/api/reservations/999")))
                .hasCause(new RuntimeException("Flight ID not found"));

    }

    @Test
    void updateReservation_UpdatesAndReturnsReservation_WhenReservationExists() throws Exception {
        given(reservationRepository.findById(anyLong())).willReturn(Optional.of(reservationMock));
        given(reservationRepository.save(any(Reservation.class))).willReturn(reservationMock);

        mockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationMock)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.checkedIn").value(true))
                .andExpect(jsonPath("$.numberOfBags").value(2))
                .andDo(print());
    }

    @Test
    void updateReservation_ReturnsNotFound_WhenReservationDoesNotExist() throws Exception {
        given(reservationRepository.findById(anyLong())).willReturn(Optional.empty());

        mockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationMock)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}