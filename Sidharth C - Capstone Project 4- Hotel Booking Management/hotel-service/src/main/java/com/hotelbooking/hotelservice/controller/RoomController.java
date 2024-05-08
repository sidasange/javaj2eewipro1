package com.hotelbooking.hotelservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbooking.hotelservice.dto.RoomDto;
import com.hotelbooking.hotelservice.entity.Room;
import com.hotelbooking.hotelservice.service.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("rooms")
public class RoomController {

	@Autowired
	RoomService roomService;

	@GetMapping("all")
	public ResponseEntity<List<Room>> getAllRooms() {
		return new ResponseEntity<List<Room>>(roomService.getAllRooms(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Room> getRoomById(@PathVariable int id) {
		return new ResponseEntity<Room>(roomService.getRoomById(id), HttpStatus.OK);
	}

	@PostMapping("add")
	public ResponseEntity<Room> createRoom(@Valid @RequestBody RoomDto room) {
		return new ResponseEntity<>(roomService.createRoom(room),HttpStatus.CREATED);
	}

	@PutMapping("update")
	public ResponseEntity<Room> updateRoom(@Valid @RequestBody Room room) {
		return new ResponseEntity<Room>(roomService.updateRoom(room), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteRoom(@PathVariable int id) {
		return new ResponseEntity<String>(roomService.deleteRoom(id), HttpStatus.OK);
	}

}
