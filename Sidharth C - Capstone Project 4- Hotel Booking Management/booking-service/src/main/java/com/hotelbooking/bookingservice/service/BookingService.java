package com.hotelbooking.bookingservice.service;

import java.util.List;

import com.hotelbooking.bookingservice.entity.Booking;
import com.hotelbooking.bookingservice.model.BookingResponse;

public interface BookingService {
	
	Booking addBooking(Booking booking);
	
	BookingResponse getBookingDetailsById(int id);
	
	List<BookingResponse> getAllBookingDetails();
	
	Booking updateBooking(Booking booking);
		
	Booking cancelBooking(int id);

}
