package com.hotelbooking.hotelservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomDto {
	@NotBlank(message = "Room Number is required")
	@Size(max = 20)
	private String roomNo;
	
	@NotBlank(message = "Room Type is required")
	@Size(max = 20)
	private String roomType;
	
	@Positive(message = "Only positive values allowed")
	private double ratePerDay;
	
	@NotNull(message = "Availability is required")
	private boolean isAvailable;
	
	@NotNull(message = "Availability is required")
	private int hotelId;

}
