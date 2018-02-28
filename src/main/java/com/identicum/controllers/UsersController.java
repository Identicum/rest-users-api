package com.identicum.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identicum.models.User;
import com.identicum.models.UserRepository;

@RestController
@RequestMapping("/users")
public class UsersController
{
	@Autowired
    UserRepository userRepository;
	
	@GetMapping(value = {"", "/"})
	public Iterable<User> index()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable(value = "id") Long userId) 
	{
	    User user = userRepository.findOne(userId);
	    if(user == null) 
	    {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(user);
	}
	
	@PostMapping(value = {"", "/"})
	public User create(@Valid @RequestBody User user) 
	{
	    return userRepository.save(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(value = "id") Long userId, 
	                                       @Valid @RequestBody User userDetails) {
		User user = userRepository.findOne(userId);
	    if(user == null) 
	    {
	        return ResponseEntity.notFound().build();
	    }
	    user.setFirstName(userDetails.getFirstName());
	    user.setLastName(userDetails.getLastName());
	    user.setUsername(userDetails.getUsername());

	    User updatedUser = userRepository.save(user);
	    return ResponseEntity.ok(updatedUser);
	}
	

}
