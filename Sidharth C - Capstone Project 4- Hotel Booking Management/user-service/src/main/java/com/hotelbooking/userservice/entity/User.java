package com.hotelbooking.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_tbl")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotBlank(message = "User name is required")
	@Size(min = 6,max = 30)
	private String userName;
	
	@Email
	private String email;
	
	@NotBlank(message = "password is required")
	@Size(min = 8,max = 30)
	private String password;
	
	@NotBlank(message = "role is required")
	private String role;
	
	@Pattern(regexp = "[0-9]{10}",message = "Inavlid mobile number,only numbers are allowed")
	private String mobile;
	
	@NotBlank(message = "role is required")
	private String address;

}
