package com.hotelbooking.bookingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotelbooking.bookingservice.model.Hotel;
import com.hotelbooking.bookingservice.model.Room;



@FeignClient(name = "HOTEL-SERVICE")
public interface HotelServiceConsumer {
	@GetMapping("rooms/{id}")
	public Room getRoomById(@PathVariable int id);
	
	@GetMapping("hotels/{id}")
	Hotel getHotelById(@PathVariable int id);
	
	@PutMapping("rooms/update")
	public Room updateRoom(@RequestBody Room room);
	
	
}
