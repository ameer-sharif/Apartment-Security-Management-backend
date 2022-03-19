package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.GuardSalaryEntity;

public interface IGuardSalaryService {
	public List getGuardSalaryEntity();
	public GuardSalaryEntity insertGuardSalaryEntity(GuardSalaryEntity guard);
	public List<GuardSalaryEntity> getGuardSalaryEntity(long l);
	public GuardSalaryEntity deleteGuardSalaryById(long userid);
	public GuardSalaryEntity getGuardSalaryById(long userId);
	public GuardSalaryEntity updateGuardSalary(GuardSalaryEntity guard);
	public GuardSalaryEntity getGuardSalaryByName(String name);
	public List<GuardSalaryEntity> getGuardSalaryByGuard(GuardSalaryEntity guard);
	public List<GuardSalaryEntity> getAllGuardSalary(Integer pageNo, Integer pageSize, String sortBy);

}