package com.hotelbooking.hotelservice.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {

	}

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}