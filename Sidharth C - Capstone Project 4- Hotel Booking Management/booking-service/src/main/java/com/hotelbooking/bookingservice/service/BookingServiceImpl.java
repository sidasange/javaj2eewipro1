package com.hotelbooking.bookingservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.bookingservice.dao.BookingDao;
import com.hotelbooking.bookingservice.entity.Booking;
import com.hotelbooking.bookingservice.exception.BookingDateNotValidException;
import com.hotelbooking.bookingservice.exception.DeadlinePassedException;
import com.hotelbooking.bookingservice.exception.ResourceNotFoundException;
import com.hotelbooking.bookingservice.model.BookingResponse;
import com.hotelbooking.bookingservice.model.Hotel;
import com.hotelbooking.bookingservice.model.Room;
import com.hotelbooking.bookingservice.model.User;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingDao bookingDao;

	@Autowired
	UserServiceConsumer userService;

	@Autowired
	HotelServiceConsumer hotelService;

	@Override

	public Booking addBooking(Booking booking) {

		Room room = hotelService.getRoomById(booking.getRoomId());
	if(room.isIsavailable()){
		//checking booking dates are valid
		if (booking.getBookedFromDate().isAfter(booking.getBookedToDate())) {
            throw new BookingDateNotValidException("Booking from date cannot be after booking to date.");
        }
		//updating room availabilty
		room.setIsavailable(false);
		hotelService.updateRoom(room);

		return bookingDao.save(booking);
	}

		throw new ResourceNotFoundException("The selected room is currently  not available for booking");
	
	}

	@Override
	public BookingResponse getBookingDetailsById(int id) {
		Optional<Booking> optionalbooking = bookingDao.findById(id);
		if (optionalbooking.isEmpty()) {
			throw new ResourceNotFoundException("Booking Details Not found with id " + id);
		}
		Booking booking = optionalbooking.get();

		User user = userService.getUserById(booking.getUserId());
		Room room = hotelService.getRoomById(booking.getRoomId());
		Hotel hotel = hotelService.getHotelById(booking.getHotelId());

		BookingResponse response = new BookingResponse();

		response.setCustomerName(user.getUserName());
		response.setMobile(user.getMobile());
		response.setHotelName(hotel.getHotelName());
		response.setRoomNo(room.getRoomNo());
		response.setRoomType(room.getRoomType());
		response.setHotelPhone1(hotel.getPhone1());
		response.setHotelPhone2(hotel.getPhone2());
		response.setAmount(booking.getAmount());
		response.setBookingDate(booking.getBookingDate());
		response.setBookedFromDate(booking.getBookedFromDate());
		response.setBookedToDate(booking.getBookedToDate());
		response.setNoOfAdults(booking.getNoOfAdults());
		response.setNoOfChildren(booking.getNoOfChildren());
		response.setStatus(booking.getStatus());
		return response;
	}

	@Override
	public List<BookingResponse> getAllBookingDetails() {
		
		List<Booking> bookings = bookingDao.findAll();
		
		List<BookingResponse> bookingresponses = new ArrayList<>();
		
		for(Booking booking : bookings) {
			
			User user = userService.getUserById(booking.getUserId());
			Room room = hotelService.getRoomById(booking.getRoomId());
			Hotel hotel = hotelService.getHotelById(booking.getHotelId());

			BookingResponse response = new BookingResponse();

			response.setCustomerName(user.getUserName());
			response.setMobile(user.getMobile());
			response.setHotelName(hotel.getHotelName());
			response.setRoomNo(room.getRoomNo());
			response.setRoomType(room.getRoomType());
			response.setHotelPhone1(hotel.getPhone1());
			response.setHotelPhone2(hotel.getPhone2());
			response.setAmount(booking.getAmount());
			response.setBookingDate(booking.getBookingDate());
			response.setBookedFromDate(booking.getBookedFromDate());
			response.setBookedToDate(booking.getBookedToDate());
			response.setNoOfAdults(booking.getNoOfAdults());
			response.setNoOfChildren(booking.getNoOfChildren());
			response.setStatus(booking.getStatus());
			
			bookingresponses.add(response);
			
		}
		return bookingresponses;
	}

	@Override
	public Booking updateBooking(Booking booking) {
		int id = booking.getBookingId();
		Optional<Booking> optionalbooking = bookingDao.findById(id);
		if (optionalbooking.isEmpty()) {
			throw new ResourceNotFoundException("Booking Details Not found with id " + id);
		}
		return bookingDao.save(booking);
	}

	@Override
	public Booking cancelBooking(int id) {
		Optional<Booking> optionalbooking = bookingDao.findById(id);
		if (optionalbooking.isEmpty()) {
			throw new ResourceNotFoundException("Booking Details Not found with id " + id);
		}
		Booking booking = optionalbooking.get();
		if (LocalDate.now().isAfter(booking.getBookedFromDate().minusDays(1))) {
			throw new DeadlinePassedException("Cancellation deadline has passed");
		}
		booking.setStatus("Canceled");
		Room room = hotelService.getRoomById(booking.getRoomId());
		room.setIsavailable(true);
		hotelService.updateRoom(room);

		return bookingDao.save(booking);
	}

}
