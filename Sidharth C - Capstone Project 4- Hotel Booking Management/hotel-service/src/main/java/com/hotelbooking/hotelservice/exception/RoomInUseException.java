package com.hotelbooking.hotelservice.exception;

public class RoomInUseException extends RuntimeException {

	public RoomInUseException() {

	}

	public RoomInUseException(String msg) {
		super(msg);
	}
}