package com.hotelbooking.hotelservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.hotelservice.dao.HotelDao;
import com.hotelbooking.hotelservice.dao.RoomDao;
import com.hotelbooking.hotelservice.dto.RoomDto;
import com.hotelbooking.hotelservice.entity.Hotel;
import com.hotelbooking.hotelservice.entity.Room;
import com.hotelbooking.hotelservice.exception.ResourceNotFoundException;
import com.hotelbooking.hotelservice.exception.RoomInUseException;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	HotelDao hotelDao;
	
	@Autowired
	RoomDao roomDao;

	@Override
	public List<Room> getAllRooms() {
	
		return roomDao.findAll();
	}

	@Override
	public Room getRoomById(int id) {
		Optional<Room> optionalroom = roomDao.findById(id);
		if(optionalroom.isEmpty()) {
		  throw new ResourceNotFoundException("Room not found with room id "+id);
		}
		return optionalroom.get();
	}

	@Override
	public Room createRoom(RoomDto roomDto) {
		Optional<Hotel> hotel = hotelDao.findById(roomDto.getHotelId());
		if(hotel.isEmpty()) {
			throw new ResourceNotFoundException("Hotel not found with hotel id "+roomDto.getHotelId());
		}
      Room room = new Room();
      
		room.setHotel(hotel.get());
		room.setRoomNo(roomDto.getRoomNo());
		room.setRoomType(roomDto.getRoomType());
		room.setRatePerDay(roomDto.getRatePerDay());
		room.setIsavailable(roomDto.isAvailable());
	
		return roomDao.save(room);
	}

	@Override
	public Room updateRoom(Room room) {
		Optional<Room> optionalroom = roomDao.findById(room.getRoomId());
		if(optionalroom.isEmpty()) {
			throw new ResourceNotFoundException("Room not found with room id "+room.getRoomId());
		}
		
		Room room2 = optionalroom.get();
		     room2.setIsavailable(room.isIsavailable());
		     room2.setRoomNo(room.getRoomNo());
		     room2.setRoomType(room.getRoomType());
		     room2.setRatePerDay(room.getRatePerDay());
		
		return roomDao.save(room2);
	}

	@Override
	public String deleteRoom(int id) {
		Optional<Room> optionalroom = roomDao.findById(id);
		if(optionalroom.isEmpty()) {
		  throw new ResourceNotFoundException("Room not found with room id "+id);
		}
		Room room = optionalroom.get();
		if(!room.isIsavailable()) {
			throw new RoomInUseException("Room is currently in use please try after some time");
		}
		
	      roomDao.deleteById(id);
		return "Room Deleted with id "+id;
	}

	

}
