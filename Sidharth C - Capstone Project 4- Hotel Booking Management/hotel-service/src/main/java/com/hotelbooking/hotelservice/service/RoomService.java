package com.hotelbooking.hotelservice.service;

import java.util.List;

import com.hotelbooking.hotelservice.dto.RoomDto;
import com.hotelbooking.hotelservice.entity.Room;

public interface RoomService {
	
	List<Room> getAllRooms();
	
	Room getRoomById(int id);
	
	Room createRoom(RoomDto roomDto);
	
	Room updateRoom(Room room);
		
	String deleteRoom(int id);

}
