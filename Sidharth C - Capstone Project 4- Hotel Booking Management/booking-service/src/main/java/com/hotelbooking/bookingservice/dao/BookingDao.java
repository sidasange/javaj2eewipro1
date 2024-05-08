package com.hotelbooking.bookingservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbooking.bookingservice.entity.Booking;

public interface BookingDao extends JpaRepository<Booking, Integer> {

}
