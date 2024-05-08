package com.hotelbooking.bookingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	
	private int userId;
    private String userName;
    private String email;
    private String password;
    private String role;
    private String mobile;
    private String address;



}