package com.identicum.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.identicum.dtos.LoginDto;
import com.identicum.models.User;
import com.identicum.service.UserRepository;

@RestController
public class AuthenticationController
{
private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    UserRepository userRepository;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@Valid @RequestBody LoginDto loginDto, Errors errors)
	{
		log.debug("Authentication request received for user {}", loginDto.getUsername());
		if(errors.hasErrors())
		{
			List<String> stringErrors = new ArrayList<>();
			for(FieldError fieldError : errors.getFieldErrors())
			{
				stringErrors.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(stringErrors);
		}
		
		List<User> users = userRepository.findByUsernameAndPassword(loginDto.getUsername(), User.hashPassword( loginDto.getPassword() ));
		if( users.size() == 1) 
		{
			log.debug("Successfully authenticated user {}", loginDto.getUsername());
			if( users.get(0).getActive())
			{
				return ResponseEntity.ok(users.get(0));
			}
			else
			{
				log.debug("User is not active"); 
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
