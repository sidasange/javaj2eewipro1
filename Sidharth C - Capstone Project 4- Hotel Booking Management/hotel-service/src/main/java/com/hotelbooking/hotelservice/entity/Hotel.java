package com.hotelbooking.hotelservice.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel_tbl")
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hotelId;
	
	@NotBlank(message = "City is required")
	private String city;
	
	@NotBlank(message = "Hotel Name is required")
	private String hotelName;
	
	@NotBlank(message = "Address is required")
	private String address;
	
	@NotBlank(message = "Description is required")
	private String description;
	
	@Positive(message = "Only positive values allowed")
	private double avgRatePerDay;
	
	@Email
	private String email;
	
	@Pattern(regexp = "[0-9]{10}",message = "Inavlid mobile number,only numbers are allowed")
	private String phone1;
	
	@Pattern(regexp = "[0-9]{10}",message = "Inavlid mobile number,only numbers are allowed")
	private String phone2;
	
	@NotBlank(message = "Website is required")
	private String website;
	
	@OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
	private List<Room> hotelRooms;

}
