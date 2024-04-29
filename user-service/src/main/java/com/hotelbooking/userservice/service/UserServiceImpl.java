package com.hotelbooking.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.userservice.dao.UserDao;
import com.hotelbooking.userservice.entity.User;
import com.hotelbooking.userservice.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public User addUser(User user) {
		
		userDao.save(user);
		return user;
	}

	@Override
	public User getUserById(int userId) {
		Optional<User> optionaluser =userDao.findById(userId);
		if(optionaluser.isEmpty()) {
			throw new ResourceNotFoundException("User not found with user id : "+userId);
		}
		return optionaluser.get();
	}

	@Override
	public List<User> getAllUsers() {
		
		return userDao.findAll();
	}

	@Override
	public User updateUser(User user) {
		
		int userId = user.getUser_id();
		Optional<User> optionaluser =userDao.findById(userId);
		if(optionaluser.isEmpty()) {
			throw new ResourceNotFoundException("User not found with user id : "+userId);
		}
		userDao.save(user);
		return user;
	}

	@Override
	public String deleteUser(int userId) {
		Optional<User> optionaluser =userDao.findById(userId);
		if(optionaluser.isEmpty()) {
			throw new ResourceNotFoundException("User not found with user id : "+userId);
		}
		userDao.delete(optionaluser.get());
		return "User deleted with user id "+userId;
	}

}
