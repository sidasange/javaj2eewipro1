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
	private int user_id;
	
//	@NotBlank
//	@Size(min = 6,max = 30)
	private String user_name;
	
	//@Email
	private String email;
	
//	@NotBlank
//	@Size(min = 8,max = 30)
	private String password;
	
	//@NotBlank
	private String role;
	
	//@Pattern(regexp = "[0-9]{10}")
	private String mobile;
	
	//@NotBlank
	private String address;

}
