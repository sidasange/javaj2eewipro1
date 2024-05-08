package com.hotelbooking.paymentservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbooking.paymentservice.entity.Payments;

public interface PaymentDao extends JpaRepository<Payments, Integer> {
	 
	boolean existsByBookingId(int bookingId);

}
