package com.bharath.flightreservation.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationDTO {
    private Long flightId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}