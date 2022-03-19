package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.GuardTrainingEntity;

public interface IGuardTrainingService {
	
	public List getGuardTrainingEntity();
	public GuardTrainingEntity insertGuardTrainingEntity(GuardTrainingEntity guard);
	public List<GuardTrainingEntity> getGuardTrainingEntity(long l);
	public GuardTrainingEntity deleteGuardById(long userid);
	public GuardTrainingEntity getGuardById(long userId);
	public GuardTrainingEntity updateGuard(GuardTrainingEntity guard);
	public GuardTrainingEntity getGuardName(String name);
	public List<GuardTrainingEntity> getGuardByGuard(GuardTrainingEntity guard);
	public List<GuardTrainingEntity> getAllGuardTraining(Integer pageNo, Integer pageSize, String sortBy);
	

}