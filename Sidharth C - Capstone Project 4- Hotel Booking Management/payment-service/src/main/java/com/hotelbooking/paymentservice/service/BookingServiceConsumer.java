package com.hotelbooking.paymentservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotelbooking.paymentservice.model.BookingResponse;


@FeignClient(name = "BOOKING-SERVICE")
public interface BookingServiceConsumer {
	
	@GetMapping("booking/{id}")
	public BookingResponse getBookingDetailsById(@PathVariable int id);

}
