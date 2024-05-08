package com.hotelbooking.bookingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
    private int roomId;
	private String roomNo;
	private String roomType;
	private double ratePerDay;
	private boolean isavailable;
	private Hotel hotel;

}
