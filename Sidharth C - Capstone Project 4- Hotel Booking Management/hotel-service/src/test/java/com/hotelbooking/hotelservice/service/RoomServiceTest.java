package com.hotelbooking.hotelservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotelbooking.hotelservice.dao.HotelDao;
import com.hotelbooking.hotelservice.dao.RoomDao;
import com.hotelbooking.hotelservice.dto.RoomDto;
import com.hotelbooking.hotelservice.entity.Hotel;
import com.hotelbooking.hotelservice.entity.Room;
import com.hotelbooking.hotelservice.exception.ResourceNotFoundException;

@SpringBootTest(properties = "eureka.client.enabled=false")
public class RoomServiceTest {
	
	@InjectMocks
    private RoomService roomService = new RoomServiceImpl();

    @Mock
    private HotelDao hotelDao;

    @Mock
    private RoomDao roomDao;

    @Test
    public void getAllRoomsTest() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, "101", "Single", 100.0, true, new Hotel()));
        rooms.add(new Room(2, "102", "Double", 150.0, true, new Hotel()));

        when(roomDao.findAll()).thenReturn(rooms);

        List<Room> result = roomService.getAllRooms();

        assertEquals(2, result.size());
        verify(roomDao).findAll();
    }
    @Test
    public void getRoomByIdTest() {
        Room room = new Room(1, "101", "Single", 100.0, true, new Hotel());
        when(roomDao.findById(1)).thenReturn(Optional.of(room));

        Room result = roomService.getRoomById(1);

        assertEquals("101", result.getRoomNo());
        assertTrue(result.isIsavailable());
    }

    @Test
    public void getRoomByIdNotFoundTest() {
        when(roomDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            roomService.getRoomById(1);
        });
    }

    @Test
    public void createRoomTest() {
    	Hotel hotel = new Hotel();
        hotel.setHotelId(1);
        
        RoomDto roomDto = new RoomDto();
        roomDto.setHotelId(1);
        roomDto.setRoomNo("101");
        roomDto.setRoomType("Single");
        roomDto.setRatePerDay(100.0);
        roomDto.setAvailable(true);
        
        when(hotelDao.findById(roomDto.getHotelId())).thenReturn(Optional.of(hotel));

        Room room = new Room();
        room.setRoomNo(roomDto.getRoomNo());
        room.setRoomType(roomDto.getRoomType());
        room.setRatePerDay(roomDto.getRatePerDay());
        room.setIsavailable(roomDto.isAvailable());
        room.setHotel(hotel); 
        
        when(roomDao.save(any(Room.class))).thenReturn(room);

        
        Room result = roomService.createRoom(roomDto);
        
        assertEquals("101", result.getRoomNo());
        assertTrue(result.isIsavailable());
        assertNotNull(result.getHotel());
    }

    @Test
    public void createRoomHotelNotFoundTest() {
    	 RoomDto roomDto = new RoomDto();
    	    roomDto.setHotelId(1); 
    	    roomDto.setRoomNo("101");
    	    roomDto.setRoomType("Single");
    	    roomDto.setRatePerDay(100.0);
    	    roomDto.setAvailable(true);
    	    
    	    when(hotelDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            roomService.createRoom(roomDto);
        });
    }

    @Test
    public void updateRoomTest() {
        Room existingRoom = new Room(1, "101", "Single", 100.0, true, new Hotel());
        when(roomDao.findById(1)).thenReturn(Optional.of(existingRoom));

        Room updatedRoom = new Room(1, "101", "Single", 100.0, false, new Hotel());
        when(roomDao.save(any(Room.class))).thenReturn(updatedRoom);

        Room result = roomService.updateRoom(updatedRoom);

        assertFalse(result.isIsavailable());
    }

    @Test
    public void updateRoomNotFoundTest() {
        Room room = new Room(1, "101", "Single", 100.0, true, new Hotel());
        when(roomDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            roomService.updateRoom(room);
        });
    }

    @Test
    public void deleteRoomTest() {
        Room room = new Room(1, "101", "Single", 100.0, true, new Hotel());
        when(roomDao.findById(1)).thenReturn(Optional.of(room));
        doNothing().when(roomDao).deleteById(1);

        String result = roomService.deleteRoom(1);

        assertEquals("Room Deleted with id 1", result);
        verify(roomDao).deleteById(1);
    }


}
