package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.GuardShiftEntity;

public interface IGuardShiftService {
	
	public List getGuardShiftService();
	public GuardShiftEntity insertGuardShiftService(GuardShiftEntity guard);
	public GuardShiftEntity getGuardShiftById(long userId);
	public GuardShiftEntity deleteGuardShiftById(long userid);
	public GuardShiftEntity updateGuardShift(GuardShiftEntity guard);
	public GuardShiftEntity getGuardShiftByName(String name);
	public List<GuardShiftEntity> getGuardShiftByGuard(GuardShiftEntity guard);
	public List<GuardShiftEntity> getAllGuardShift(Integer pageNo, Integer pageSize, String sortBy);

}