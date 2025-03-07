package com.bharath.flightreservation.dtos;

import lombok.Data;

@Data
public class ReservationUpdateRequest {
	
	private Long id;
	private Boolean checkedIn;
	private int numberOfBags;	

}
