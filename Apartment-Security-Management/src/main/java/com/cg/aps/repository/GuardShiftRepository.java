package com.cg.aps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aps.entities.GuardShiftEntity;
import com.cg.aps.entities.GuardTrainingEntity;



public interface GuardShiftRepository extends JpaRepository<GuardShiftEntity,Long> {
	public GuardShiftEntity name(String name);

}