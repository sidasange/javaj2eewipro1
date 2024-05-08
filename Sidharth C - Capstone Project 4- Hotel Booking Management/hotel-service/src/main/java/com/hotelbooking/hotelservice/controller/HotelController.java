package com.hotelbooking.hotelservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbooking.hotelservice.entity.Hotel;
import com.hotelbooking.hotelservice.service.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("hotels")
public class HotelController {

	@Autowired
	HotelService hotelService;

	@GetMapping("all")
	public ResponseEntity<List<Hotel>> getAllHotels() {
		List<Hotel> hotels = hotelService.getAllHotels();
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable int id) {
		Hotel hotel = hotelService.getHotelById(id);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}

	@PostMapping("add")
	public ResponseEntity<Hotel> createHotel(@Valid @RequestBody Hotel hotel) {
		return new ResponseEntity<Hotel>(hotelService.createHotel(hotel), HttpStatus.CREATED);
	}

	@PutMapping("update")
	public ResponseEntity<Hotel> updateHotel(@Valid @RequestBody Hotel hotel) {
		return new ResponseEntity<Hotel>(hotelService.updateHotel(hotel), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteHotel(@PathVariable int id) {
		return new ResponseEntity<String>(hotelService.deleteHotel(id), HttpStatus.OK);
	}

}
