package com.bharath.flightreservation.dtos;

import lombok.Data;

@Data
public class ReservationRequest {
    private Long flightId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    
}
