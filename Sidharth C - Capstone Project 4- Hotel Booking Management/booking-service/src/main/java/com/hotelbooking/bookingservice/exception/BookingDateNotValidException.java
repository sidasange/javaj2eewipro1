package com.hotelbooking.bookingservice.exception;

public class BookingDateNotValidException extends RuntimeException {

	public BookingDateNotValidException() {

	}

	public BookingDateNotValidException(String msg) {
		super(msg);
	}
}