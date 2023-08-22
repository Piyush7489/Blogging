package com.durgesh.blog.services;

import java.util.List;

import com.durgesh.blog.payloads.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO user);
	UserDTO updateuser(UserDTO user, Integer userId);
	UserDTO getUserById(Integer userId);
	List<UserDTO> getAllUser();
	void deleteUser(Integer userId);
}
