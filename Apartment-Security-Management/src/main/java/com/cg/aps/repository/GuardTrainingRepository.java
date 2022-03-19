package com.cg.aps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.aps.entities.GuardTrainingEntity;

public interface GuardTrainingRepository extends JpaRepository<GuardTrainingEntity,Long> {
	
  public GuardTrainingEntity name(String name);

}