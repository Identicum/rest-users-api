package com.identicum.models;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	List<User> findByLastName(@Param("lastName") String name);

}
