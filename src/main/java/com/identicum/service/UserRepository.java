package com.identicum.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.identicum.models.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	List<User> findByUsernameAndPassword(String username, String password);
	
	List<User> findByUsernameContaining(@Param("username") String username);
	
	User findByUsernameIgnoreCase(String username);
	
}
