package com.identicum.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.identicum.models.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	List<User> findByUsernameContaining(@Param("username") String username);

}
