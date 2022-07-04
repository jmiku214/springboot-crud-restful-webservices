package com.aws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.entity.User;
import com.aws.exception.ResourceNotFoundException;
import com.aws.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/all")
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	@GetMapping("/getBy/{id}")
	public User getById(@PathVariable("id") int id) {
		User user=userRepository.findById(id).get();
		if(user==null)
		{
			throw new ResourceNotFoundException("User not found with id:"+id);
		}
		return user;
	}
	
	@PostMapping("/create")
	public User save(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/update/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") int id)
	{
		User users=userRepository.findById(id).get();
		if(users==null) {
			throw new ResourceNotFoundException("User is not Exist with id:"+id);
		}
		users.setFirstName(user.getFirstName());
		users.setLastName(user.getLastName());
		users.setEmail(user.getEmail());
		return userRepository.save(user);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id){
		User user=userRepository.findById(id).get();
		if(user==null)
		{
			throw new ResourceNotFoundException("User is not exist with id:"+id);
		}
		this.userRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
