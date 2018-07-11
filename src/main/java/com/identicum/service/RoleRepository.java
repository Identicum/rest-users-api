package com.identicum.service;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.identicum.models.Role;


public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

	List<Role> findByNameContaining(@Param("name") String name);
}
