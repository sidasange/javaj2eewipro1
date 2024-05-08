package com.hotelbooking.hotelservice.service;

import java.util.List;

import com.hotelbooking.hotelservice.entity.Hotel;

public interface HotelService {
	
	List<Hotel> getAllHotels();
	
	Hotel getHotelById(int id);
	
	Hotel createHotel(Hotel hotel);
	
	Hotel updateHotel(Hotel hotel);
	
	String deleteHotel(int id);
	

}
