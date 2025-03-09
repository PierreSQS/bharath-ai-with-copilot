package com.bharath.flightreservation.dtos;

import lombok.Data;

@Data
public class ReservationUpdateDTO {
    private Long id;
    private boolean checkedIn;
    private int numberOfBags;
}