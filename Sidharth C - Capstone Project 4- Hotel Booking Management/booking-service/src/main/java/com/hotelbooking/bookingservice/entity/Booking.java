package com.hotelbooking.bookingservice.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "booking_tbl")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	@NotNull(message = "Hotel Id is required")
	private int hotelId;
	
	@NotNull(message = "Room Id is required")
	private int roomId;
	
	@NotNull(message = "User Id is required")
	private int userId;
	
	private LocalDate bookingDate;
	
	private LocalDate bookedFromDate;
	
	private LocalDate bookedToDate;
	
	@NotNull(message = "Number of adults is required")
	private int noOfAdults;
	
	@NotNull(message = "Number of children is required")
    private int noOfChildren;
	
	@Positive(message = "Amount :Only positive values allowed")
    private double amount;
    
    private String status;
    
 
}
