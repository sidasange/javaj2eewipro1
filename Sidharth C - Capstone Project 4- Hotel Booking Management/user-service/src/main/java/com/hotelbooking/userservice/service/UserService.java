package com.hotelbooking.userservice.service;

import java.util.List;

import com.hotelbooking.userservice.entity.User;

public interface UserService {
	
	User addUser(User user);

	User getUserById(int userId);

	List<User> getAllUsers();

	User updateUser(User user);

	String deleteUser(int userId);

}
