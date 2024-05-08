package com.hotelbooking.hotelservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_tbl")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;
	
	@NotBlank(message = "Room Number is required")
	@Size(max = 20)
	private String roomNo;
	
	@NotBlank(message = "Room Type is required")
	@Size(max = 20)
	private String roomType;
	
	@Positive(message = "Only positive values allowed")
	private double ratePerDay;
	
	@NotNull(message = "Availability is required")
	private boolean isavailable;
	//private Blob photo;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

}
