package com.hotelbooking.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotelbooking.userservice.dao.UserDao;
import com.hotelbooking.userservice.entity.User;
import com.hotelbooking.userservice.exception.ResourceNotFoundException;

@SpringBootTest(properties = "eureka.client.enabled=false")
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Mock
	private UserDao userDao;

	@Test
	public void getUserDetailsTest() {

		User user = new User();

		user.setUserName("Rohit");
		user.setMobile("9856231470");
		user.setUserId(100);
		user.setEmail("gdcgf@gmail.com");
		user.setAddress("ehgfchkuyjh");
		user.setRole("customer");

		when(userDao.findById(100)).thenReturn(Optional.of(user));

		User testuser = userService.getUserById(100);

		assertEquals("Rohit", testuser.getUserName());

	}

	@Test
	public void testGetUserDetailsException() {

		when(userDao.findById(101)).thenThrow(new ResourceNotFoundException("User details not found with id 101"));
		assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(101));
	}

	@Test
	public void getAllUserDetailsTest() {
		
		User user = new User();
		
		user.setUserName("Rohit");
		user.setMobile("9856231470");
		user.setUserId(100);
		user.setEmail("gdcgf@gmail.com");
		user.setAddress("ehgfchkuyjh");
		user.setRole("customer");
		
      User user1 = new User();
		
		user1.setUserName("Rohit");
		user1.setMobile("9856231470");
		user1.setUserId(101);
		user1.setEmail("gdcgf@gmail.com");
		user1.setAddress("ehgfchkuyjh");
		user1.setRole("customer");
		
        User user2 = new User();
		
		user2.setUserName("Rohit");
		user2.setMobile("9856231470");
		user2.setUserId(102);
		user2.setEmail("gdcgf@gmail.com");
		user2.setAddress("ehgfchkuyjh");
		user2.setRole("customer");
		
		List<User> users = new ArrayList<>();
		
		users.add(user2);
		users.add(user1);
		users.add(user);
		
		when(userDao.findAll()).thenReturn(users);
		
		List<User> testlist =userService.getAllUsers();
		
		assertEquals(3, testlist.size());
		
	}
	
	@Test
	public void deleteUserTest() {
		
		User user = new User();

		user.setUserName("Rohit");
		user.setMobile("9856231470");
		user.setUserId(100);
		user.setEmail("gdcgf@gmail.com");
		user.setAddress("ehgfchkuyjh");
		user.setRole("customer");
		
		doNothing().when(userDao).delete(user);;
		
		
		when(userDao.findById(100)).thenReturn(Optional.of(user));
		userService.deleteUser(100);
		
		verify(userDao, times(1)).findById(100);

		verify(userDao, times(1)).delete(user);
		
	}
	
	@Test
	public void deleteUserExceptionTest() {
		User user = new User();
		
		when(userDao.findById(100)).thenThrow(new ResourceNotFoundException("User Details Not found with id 100"));
		
		assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(100));
		verify(userDao, times(0)).delete(user);

	}

}
