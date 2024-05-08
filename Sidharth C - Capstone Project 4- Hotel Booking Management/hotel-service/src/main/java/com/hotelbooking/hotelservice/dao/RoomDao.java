package com.hotelbooking.hotelservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbooking.hotelservice.entity.Room;

public interface RoomDao extends JpaRepository<Room, Integer> {
	
	

}
