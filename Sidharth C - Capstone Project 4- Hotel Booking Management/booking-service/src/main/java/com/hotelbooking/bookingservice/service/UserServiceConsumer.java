package com.hotelbooking.bookingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotelbooking.bookingservice.model.User;


@FeignClient(name = "USER-SERVICE")
public interface UserServiceConsumer {
	@GetMapping("user/{id}")
	public User getUserById(@PathVariable int id);

}
