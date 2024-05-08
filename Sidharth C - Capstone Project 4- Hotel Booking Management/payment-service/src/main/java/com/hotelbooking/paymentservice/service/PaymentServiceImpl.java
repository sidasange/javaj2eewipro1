package com.hotelbooking.paymentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.paymentservice.dao.PaymentDao;
import com.hotelbooking.paymentservice.entity.Payments;
import com.hotelbooking.paymentservice.exception.PaymentFailedException;
import com.hotelbooking.paymentservice.exception.ResourceNotFoundException;
import com.hotelbooking.paymentservice.model.BookingResponse;
import com.hotelbooking.paymentservice.model.PaymentResponse;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentDao paymentDao;

	@Autowired
	BookingServiceConsumer bookingService;

	@Override
	public PaymentResponse makePayment(Payments payment) {
		
		if(paymentDao.existsByBookingId(payment.getBookingId())) {
			throw new PaymentFailedException("Payment already exists for this booking. Duplicate payments are not allowed.");
		}

		BookingResponse response = bookingService.getBookingDetailsById(payment.getBookingId());

		if (response.getAmount() != payment.getPAmount()) {
			throw new PaymentFailedException(
					"Sorry Payment Failed,You need to make Payment of " + response.getAmount());
		}
		paymentDao.save(payment);
		PaymentResponse presponse = new PaymentResponse();

		presponse.setMessage("Payment Successfull");
		presponse.setBookingResponse(response);

		return presponse;
	}

	@Override
	public Payments viewPaymentsById(int id) {

		Optional<Payments> optionalpayment = paymentDao.findById(id);
		if (optionalpayment.isEmpty()) {
			throw new ResourceNotFoundException("Payment Details Not found with Id " + id);
		}
		return optionalpayment.get();
	}

	@Override
	public List<Payments> viewAllPayments() {
	
		return paymentDao.findAll();
	}

}
