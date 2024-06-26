package com.hotelbooking.hotelservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.hotelbooking.hotelservice.entity.Hotel;
import com.hotelbooking.hotelservice.entity.Room;
import com.hotelbooking.hotelservice.exception.ResourceNotFoundException;

@SpringBootTest(properties = "eureka.client.enabled=false")
public class HotelServiceTest {

	@InjectMocks
	private HotelService hotelService = new HotelServiceImpl();

	@Mock
	private HotelDao hotelDao;

	@Test
	public void getAllHotelsTest() {
		List<Hotel> hotels = new ArrayList<>();
		hotels.add(new Hotel(1, "New York", "Hotel California", "Some address", "Nice place", 200.0,
				"contact@hotelcal.com", "1234567890", "0987654321", "www.hotelcal.com", new ArrayList<Room>()));
		hotels.add(new Hotel(2, "Los Angeles", "Hotel New York", "Another address", "Great stay", 150.0,
				"info@hotelnyc.com", "1112223330", "4445556661", "www.hotelnyc.com", new ArrayList<Room>()));

		when(hotelDao.findAll()).thenReturn(hotels);

		List<Hotel> result = hotelService.getAllHotels();

		assertEquals(2, result.size());
		assertEquals("New York", result.get(0).getCity());
		verify(hotelDao).findAll();
	}
	
	@Test
    public void getHotelByIdTest() {
        Hotel hotel = new Hotel(1, "New York", "Hotel California", "Some address", "Nice place", 200.0, "contact@hotelcal.com", "1234567890", "0987654321", "www.hotelcal.com", new ArrayList<Room>());
        when(hotelDao.findById(1)).thenReturn(Optional.of(hotel));

        Hotel result = hotelService.getHotelById(1);

        assertEquals("Hotel California", result.getHotelName());
        assertEquals("New York", result.getCity());
    }
	
	@Test
    public void getHotelByIdNotFoundTest() {
        when(hotelDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            hotelService.getHotelById(1);
        });
    }

    @Test
    public void createHotelTest() {
        Hotel hotel = new Hotel(1, "New York", "Hotel California", "Some address", "Nice place", 200.0, "contact@hotelcal.com", "1234567890", "0987654321", "www.hotelcal.com", new ArrayList<Room>());
        when(hotelDao.save(any(Hotel.class))).thenReturn(hotel);

        Hotel result = hotelService.createHotel(hotel);

        assertEquals("Hotel California", result.getHotelName());
        verify(hotelDao).save(hotel);
    }

    @Test
    public void updateHotelTest() {
        Hotel existingHotel = new Hotel(1, "New York", "Hotel California", "Some address", "Nice place", 200.0, "contact@hotelcal.com", "1234567890", "0987654321", "www.hotelcal.com", new ArrayList<Room>());
        when(hotelDao.findById(1)).thenReturn(Optional.of(existingHotel));
        when(hotelDao.save(any(Hotel.class))).thenReturn(existingHotel);

        Hotel result = hotelService.updateHotel(existingHotel);

        assertEquals("Hotel California", result.getHotelName());
        verify(hotelDao).save(existingHotel);
    }

    @Test
    public void updateHotelNotFoundTest() {
        Hotel newHotel = new Hotel(1, "New York", "Hotel California", "Some address", "Nice place", 200.0, "contact@hotelcal.com", "1234567890", "0987654321", "www.hotelcal.com", new ArrayList<Room>());
        when(hotelDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            hotelService.updateHotel(newHotel);
        });
    }

    @Test
    public void deleteHotelTest() {
        Hotel existingHotel = new Hotel(1, "New York", "Hotel California", "Some address", "Nice place", 200.0, "contact@hotelcal.com", "1234567890", "0987654321", "www.hotelcal.com", new ArrayList<Room>());
        when(hotelDao.findById(1)).thenReturn(Optional.of(existingHotel));
        doNothing().when(hotelDao).deleteById(1);

        String result = hotelService.deleteHotel(1);

        assertEquals("Hotel deleted with id 1", result);
        verify(hotelDao).deleteById(1);
    }

    @Test
    public void deleteHotelNotFoundTest() {
        when(hotelDao.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            hotelService.deleteHotel(1);
        });
    }

}
