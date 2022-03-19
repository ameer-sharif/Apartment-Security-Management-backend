package com.cg.aps.repository;

import com.cg.aps.entities.UserEntity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	


	UserEntity firstName(String firstName);



	String save(String token);



	UserEntity emailId(String email);
	
	
}