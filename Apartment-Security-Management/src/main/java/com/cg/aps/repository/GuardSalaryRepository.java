package com.cg.aps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aps.entities.GuardSalaryEntity;
import com.cg.aps.entities.GuardShiftEntity;


public interface GuardSalaryRepository extends JpaRepository<GuardSalaryEntity,Long> {
	
	public GuardSalaryEntity name(String name);

}