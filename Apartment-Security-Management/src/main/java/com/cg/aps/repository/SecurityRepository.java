package com.cg.aps.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aps.entities.SecurityEntity;

public interface SecurityRepository extends JpaRepository<SecurityEntity,String>{
	

}