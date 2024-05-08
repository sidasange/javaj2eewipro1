package com.hotelbooking.paymentservice.service;

import java.util.List;

import com.hotelbooking.paymentservice.entity.Payments;
import com.hotelbooking.paymentservice.model.PaymentResponse;

public interface PaymentService {
	
	PaymentResponse makePayment(Payments payment);
	
	Payments viewPaymentsById(int id);
	
	List<Payments> viewAllPayments();

}
