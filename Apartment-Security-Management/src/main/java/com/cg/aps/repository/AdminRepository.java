package com.cg.aps.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.aps.entities.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

	AdminEntity emailId(String emailId);

	AdminEntity findByPasswordAndEmailId(String password, String emailId);

	
	@Query("Select a from AdminEntity a where a.emailId = :emailId")
	public AdminEntity getAdminByEmailId(@Param("emailId") String emailId);

	
	
}