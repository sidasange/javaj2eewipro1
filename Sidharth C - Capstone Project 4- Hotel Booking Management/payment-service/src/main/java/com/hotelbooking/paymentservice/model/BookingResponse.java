package com.hotelbooking.paymentservice.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookingResponse {
	
	private String customerName;
	private String mobile; 
	private String hotelName;
	private String roomNo;
	private String roomType;
	private double amount;
	private String hotelPhone1;
	private String hotelPhone2;
	
    private LocalDate bookingDate;
	private LocalDate bookedFromDate;
	private LocalDate bookedToDate;
	private int noOfAdults;
    private int noOfChildren;
    private String status;



}
