package com.hotelbooking.paymentservice.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	    private int bookingId;
		private int hotelId;
		private int roomId;
		private int userId;
		private LocalDate bookingDate;		
		private LocalDate bookedFromDate;	
		private LocalDate bookedToDate;		
		private int noOfAdults;
	    private int noOfChildren;
	    private double amount;	    
	    private String status;
}

