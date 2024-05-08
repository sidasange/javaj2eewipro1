package com.hotelbooking.hotelservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.hotelservice.dao.HotelDao;
import com.hotelbooking.hotelservice.dao.RoomDao;
import com.hotelbooking.hotelservice.entity.Hotel;
import com.hotelbooking.hotelservice.exception.ResourceNotFoundException;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelDao hotelDao;

	@Autowired
	RoomDao roomDao;

	@Override
	public List<Hotel> getAllHotels() {

		return hotelDao.findAll();
	}

	@Override
	public Hotel getHotelById(int id) {
		Optional<Hotel> optionalhotel = hotelDao.findById(id);
		if (optionalhotel.isEmpty()) {
			throw new ResourceNotFoundException("Hotel not found with id " + id);
		}
		return optionalhotel.get();
	}

	@Override
	public Hotel createHotel(Hotel hotel) {

		return hotelDao.save(hotel);
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {
		int id = hotel.getHotelId();
		Optional<Hotel> optionalhotel = hotelDao.findById(id);
		if (optionalhotel.isEmpty()) {
			throw new ResourceNotFoundException("Hotel not found with id " + id);
		}
		hotelDao.save(hotel);
		return hotel;
	}

	@Override
	public String deleteHotel(int id) {
		Optional<Hotel> optionalhotel = hotelDao.findById(id);
		if (optionalhotel.isEmpty()) {
			throw new ResourceNotFoundException("Hotel not found with id " + id);
		}
		hotelDao.deleteById(id);

		return "Hotel deleted with id " + id;
	}



}
