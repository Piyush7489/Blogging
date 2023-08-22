package com.durgesh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.blog.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
