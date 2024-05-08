package com.hotelbooking.bookingservice.model;

import java.util.List;


import lombok.Data;
@Data
public class Hotel {
	
	private int hotelId;
	private String city;
	private String hotelName;
	private String address;
	private String description;
	private double avgRatePerDay;
	private String email;
	private String phone1;
	private String phone2;
	private String website;
	
	private List<Room> hotelRooms;

}
