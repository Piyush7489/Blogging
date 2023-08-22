package com.durgesh.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.durgesh.blog.payloads.APIResponse;
import com.durgesh.blog.payloads.UserDTO;
import com.durgesh.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService us;
//	POST - CREATE USER

	@PostMapping()
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) {
		UserDTO createdUser = this.us.createUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

//	UPDATE - UPDATE USER
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto,@PathVariable Integer userId)
	{
		UserDTO updateduser = this.us.updateuser(userDto, userId);
		return ResponseEntity.ok(updateduser);
	}
//	GET - GET USER/ALL USER
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUser()
	{
		return ResponseEntity.ok(this.us.getAllUser());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.us.getUserById(userId));
	}
	
//	DELETE - DELETE USER
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId)
	{
		this.us.deleteUser(userId);
//		return new ResponseEntity(Map.of("Message","User Deleted Successfully"),HttpStatus.OK);
		return new ResponseEntity<APIResponse>(new APIResponse("User Deleted Successfully",true),HttpStatus.OK);
	}

}
