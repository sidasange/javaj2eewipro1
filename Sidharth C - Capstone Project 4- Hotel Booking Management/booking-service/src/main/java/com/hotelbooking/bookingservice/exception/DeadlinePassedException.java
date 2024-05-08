package com.hotelbooking.bookingservice.exception;

public class DeadlinePassedException extends RuntimeException {

	public DeadlinePassedException() {

	}

	public DeadlinePassedException(String msg) {
		super(msg);
	}
}