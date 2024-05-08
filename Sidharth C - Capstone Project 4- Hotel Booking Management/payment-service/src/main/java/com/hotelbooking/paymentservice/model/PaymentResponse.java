package com.hotelbooking.paymentservice.model;

import lombok.Data;

@Data
public class PaymentResponse {
	
	private String message;
	private BookingResponse bookingResponse;

}
