package com.hotelbooking.bookingservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotelbooking.bookingservice.dao.BookingDao;
import com.hotelbooking.bookingservice.entity.Booking;
import com.hotelbooking.bookingservice.exception.DeadlinePassedException;
import com.hotelbooking.bookingservice.exception.ResourceNotFoundException;
import com.hotelbooking.bookingservice.model.BookingResponse;
import com.hotelbooking.bookingservice.model.Hotel;
import com.hotelbooking.bookingservice.model.Room;
import com.hotelbooking.bookingservice.model.User;

@SpringBootTest(properties = "eureka.client.enabled=false")
public class BookingServiceTest {

	@InjectMocks
	private BookingServiceImpl bookingService;

	@Mock
	private BookingDao bookingDao;

	@Mock
	private UserServiceConsumer userService;

	@Mock
	private HotelServiceConsumer hotelService;

	@Test
	public void testAddBooking() {

		User user = new User(1, "Rohit", "1234567890@gmail", "6563133", "user", "84121211", "ghgvvnvn");
		Room room = new Room(1, "101", "Deluxe", 100.0, true, null);
		Booking booking = new Booking(1, 1, 1, 1, LocalDate.now(), LocalDate.now().plusDays(2),
				LocalDate.now().plusDays(5), 2, 1, 200.0, "Booked");

		when(userService.getUserById(1)).thenReturn(user);
		when(hotelService.getRoomById(1)).thenReturn(room);

		// used to return the updated room
		when(hotelService.updateRoom(any(Room.class))).thenAnswer(invocation -> invocation.getArgument(0));
		when(bookingDao.save(any(Booking.class))).thenReturn(booking);

		Booking savedBooking = bookingService.addBooking(booking);

		assertEquals("Booked", savedBooking.getStatus());
		assertFalse(room.isIsavailable());
	}

	@Test
	void addBookingRoomNotAvailableTest() {
		Booking booking = new Booking(1, 1, 1, 1, LocalDate.now(), LocalDate.now().plusDays(5),
				LocalDate.now().plusDays(10), 2, 1, 200.0, "Booked");
		Room room = new Room(1, "101", "Deluxe", 150.0, false, new Hotel());

		when(hotelService.getRoomById(1)).thenReturn(room);

		assertThrows(ResourceNotFoundException.class, () -> bookingService.addBooking(booking));
	}

	@Test
	void getBookingDetailsByIdTest() {
		Booking booking = new Booking(1, 1, 1, 1, LocalDate.now(), LocalDate.now().plusDays(5),
				LocalDate.now().plusDays(10), 2, 1, 200.0, "Booked");
		User user = new User(1, "rohit", "rt@gmail.com", "password", "user", "5555555", "gcgcc");
		Hotel hotel = new Hotel();
		hotel.setHotelId(1);

		Room room = new Room(1, "101", "Deluxe", 150.0, true, new Hotel());

		when(bookingDao.findById(1)).thenReturn(Optional.of(booking));
		when(userService.getUserById(1)).thenReturn(user);
		when(hotelService.getRoomById(1)).thenReturn(room);
		when(hotelService.getHotelById(1)).thenReturn(hotel);

		BookingResponse response = bookingService.getBookingDetailsById(1);

		assertEquals("rohit", response.getCustomerName());
		assertEquals("5555555", response.getMobile());
		verify(userService).getUserById(1);
		verify(hotelService).getRoomById(1);
		verify(hotelService).getHotelById(1);
	}

	@Test
	void cancelBookingDeadlinePassedTest() {
		Booking booking = new Booking(1, 1, 1, 1, LocalDate.now(), LocalDate.now().minusDays(2),
				LocalDate.now().plusDays(5), 2, 1, 200.0, "Booked");

		when(bookingDao.findById(1)).thenReturn(Optional.of(booking));

		assertThrows(DeadlinePassedException.class, () -> bookingService.cancelBooking(1));
	}
}
