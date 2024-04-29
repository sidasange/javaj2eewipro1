package com.hotelbooking.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbooking.userservice.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
