package com.durgesh.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durgesh.blog.entity.User;
import com.durgesh.blog.exceptions.ResourceNotFoundException;
import com.durgesh.blog.payloads.UserDTO;
import com.durgesh.blog.repository.UserRepo;
import com.durgesh.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ModelMapper mapper;
	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.repo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDTO updateuser(UserDTO userDto, Integer userId) {
		User user = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User savedUser = this.repo.save(user);
		return userToDto(savedUser);
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id", userId));
		return userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<User> users = this.repo.findAll();
		List<UserDTO> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
//		User user = 
		this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id", userId));
		this.repo.deleteById(userId);
	}

	private User dtoToUser(UserDTO userDto) {
		User user = this.mapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}

	public UserDTO userToDto(User user) {
		UserDTO userDto = this.mapper.map(user, UserDTO.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
