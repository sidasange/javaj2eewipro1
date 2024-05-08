package com.hotelbooking.hotelservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbooking.hotelservice.entity.Hotel;

public interface HotelDao extends JpaRepository<Hotel, Integer> {

}
