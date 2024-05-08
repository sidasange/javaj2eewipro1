package com.hotelbooking.paymentservice.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {

	}

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}