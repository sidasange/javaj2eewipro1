package com.hotelbooking.userservice.controller;

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

import com.hotelbooking.userservice.entity.User;
import com.hotelbooking.userservice.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("add")
	public ResponseEntity<User> addUser(@RequestBody User user){
		
		 User adduser = userService.addUser(user);
		 
		 return new ResponseEntity<>(adduser,HttpStatus.CREATED);
		
	}
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id){
		
		User user = userService.getUserById(id);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<User>> getAllUsers(){
		
		List<User> users = userService.getAllUsers();
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@PutMapping("edit")
	public ResponseEntity<User> editUser(@RequestBody User user){
		User edituser = userService.updateUser(user);
		
		return new ResponseEntity<User>(edituser,HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id){
		
		String response = userService.deleteUser(id);
		
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}

}
