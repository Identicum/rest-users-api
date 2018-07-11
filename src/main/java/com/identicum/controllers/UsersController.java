package com.identicum.controllers;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.identicum.models.Role;
import com.identicum.models.User;
import com.identicum.service.RoleRepository;
import com.identicum.service.UserRepository;

@RestController
@RequestMapping("/users")
public class UsersController
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    RoleRepository roleRepository;
	
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
	public ResponseEntity<?> create(@Valid @RequestBody User user) 
	{
		log.debug("Accediendo a create() con User = {}", user.toString());
		if( userRepository.findByUsernameIgnoreCase( user.getUsername() ).size() > 0)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		userRepository.save(user);
	    return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(value = "id") Long userId, 
	                                       @Valid @RequestBody User userDetails) {
		
		log.debug("Accediendo a update() con User = {}", userDetails.toString());
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
	
	@PatchMapping("/{id}")
	public ResponseEntity<User> patch(@PathVariable(value = "id") Long userId, 
	                                       @RequestBody Map<String, String> changes) {
		
		log.debug("Accediendo a update() con deltas = {}", changes.toString());
		User user = userRepository.findOne(userId);
	    if(user == null) 
	    {
	        return ResponseEntity.notFound().build();
	    }
	    if(changes.containsKey("firstName")) user.setFirstName(changes.get("firstName"));
	    if(changes.containsKey("lastName")) user.setLastName(changes.get("lastName"));
	    if(changes.containsKey("username")) user.setUsername(changes.get("username"));
	    if(changes.containsKey("email")) user.setEmail(changes.get("email"));
	    
	    User updatedUser = userRepository.save(user);
	    return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId)
	{
		log.debug("Accediendo a delete() con user = {}", userId);
		User user = userRepository.findOne(userId);
	    if(user == null) 
	    {
	        return ResponseEntity.notFound().build();
	    }
	    userRepository.delete(user);
	    return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/roles")
	public ResponseEntity<User> assignRole(@PathVariable(value = "id") Long userId, @RequestBody Role role) 
	{
		log.debug("Accediendo a assignRole() con User = {} y Role = {}", userId, role.getId());
		User user = userRepository.findOne(userId);
	    if(user == null) 
	    {
	        return ResponseEntity.notFound().build();
	    }
	    
	    role = roleRepository.findOne(role.getId());
	    if(role == null) 
	    {
	        return ResponseEntity.notFound().build();
	    }
	    
	    user.getRoles().add(role);
	    return ResponseEntity.ok(userRepository.save(user));
	}
	
	@DeleteMapping("/{id}/roles/{roleId}")
	public ResponseEntity<User> revokeRole(@PathVariable(value = "id") Long userId, @PathVariable(value = "roleId") Long roleId) 
	{
		log.debug("Accediendo a revokeRole() con User = {} y Role = {}", userId, roleId);
		User user = userRepository.findOne(userId);
	    if(user == null) 
	    {
	        return ResponseEntity.notFound().build();
	    }
	    
	    Role role = roleRepository.findOne(roleId);
	    if(role == null) 
	    {
	        return ResponseEntity.notFound().build();
	    }
	    
	    user.getRoles().remove(role);
	    return ResponseEntity.ok(userRepository.save(user));
	}

}
