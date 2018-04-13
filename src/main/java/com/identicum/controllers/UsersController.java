package com.identicum.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.identicum.models.User;
import com.identicum.service.UserRepository;

@RestController
@RequestMapping("/users")
public class UsersController
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    UserRepository userRepository;
	
	@GetMapping(value = {"", "/"})
	public Iterable<User> index(@RequestParam("username") Optional<String> username)
	{
		log.debug("Accediendo a index() con username = {}", username);
		return userRepository.findByUsernameContaining(username.orElse(""));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable(value = "id") Long userId) 
	{
		log.debug("Accediendo a get() con id = {}", userId);
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
		log.debug("Accediendo a create() con User = {}", user.toString());
	    return userRepository.save(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(value = "id") Long userId, 
	                                       @Valid @RequestBody User userDetails) {
		
		log.debug("Accediendo a update() con User = ", userDetails);
		User user = userRepository.findOne(userId);
	    if(user == null) 
	    {
	        return ResponseEntity.notFound().build();
	    }
	    user.setFirstName(userDetails.getFirstName());
	    user.setLastName(userDetails.getLastName());
	    user.setUsername(userDetails.getUsername());
	    user.setEmail(userDetails.getEmail());
	    user.setActive(userDetails.getActive());
	    
	    User updatedUser = userRepository.save(user);
	    return ResponseEntity.ok(updatedUser);
	}
	

}
