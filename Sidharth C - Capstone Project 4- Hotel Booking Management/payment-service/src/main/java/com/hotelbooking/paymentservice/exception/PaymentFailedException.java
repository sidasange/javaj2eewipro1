package com.hotelbooking.paymentservice.exception;

public class PaymentFailedException extends RuntimeException {

	public PaymentFailedException() {

	}

	public PaymentFailedException(String msg) {
		super(msg);
	}
}