package com.cg.aps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cg.aps.controller.GuardTrainingController;
import com.cg.aps.entities.GuardSalaryEntity;
import com.cg.aps.entities.GuardTrainingEntity;
import com.cg.aps.repository.GuardSalaryRepository;

@Service
public class GuardSalaryService {
	@Autowired
	GuardSalaryRepository guardSalaryRepository;
	Logger logger=LoggerFactory.getLogger(GuardTrainingController.class);
	
	public List getGuardSalaryEntity()
	{
		List<GuardSalaryEntity> guardexist=guardSalaryRepository.findAll();
		return guardexist;
	}
	
	public GuardSalaryEntity insertGuardSalaryEntity(GuardSalaryEntity guard)
	{
		logger.info("service insertguard started");
		List<GuardSalaryEntity> guardexist=getGuardSalaryEntity(guard.getUserId());
		if(guardexist==null)
		{
			guard=guardSalaryRepository.save(guard);
		}
		return guard;
	}
	
	public List<GuardSalaryEntity> getGuardSalaryEntity(long l)
	{
		return null;
	}
	
	public GuardSalaryEntity deleteGuardSalaryById(long userid) {
		logger.info("service deleteguard by ID started");
		GuardSalaryEntity guard = getGuardSalaryById(userid);
		if (guard != null)
			guardSalaryRepository.deleteById(userid);
		return guard;
	}
	
	public GuardSalaryEntity getGuardSalaryById(long userId) {
		logger.info("service getguard by ID started");
		Optional<GuardSalaryEntity> guard = guardSalaryRepository.findById(userId);
		return (guard.isPresent()) ? guard.get() : null;
	}
	
	public GuardSalaryEntity updateGuardSalary(GuardSalaryEntity guard) {
		logger.info("service update guard started");
		GuardSalaryEntity guardexist = getGuardSalaryById(guard.getUserId());
		if (guardexist != null) {
			guard = guardSalaryRepository.save(guard);
		}
		return guard;
	}
	
    public GuardSalaryEntity getGuardSalaryByName(String name) {
    	GuardSalaryEntity guard = guardSalaryRepository.name(name);
		return guard;
	}
	
	public List<GuardSalaryEntity> getGuardSalaryByGuard(GuardSalaryEntity guard) {
		logger.info("service getguard by Guard started");
		
//		FlatEntity employee = new FlatEntity();
//		 employee.setflat("Erika");
		 Example<GuardSalaryEntity> guardExample = Example.of(guard);
		List<GuardSalaryEntity> guardList = guardSalaryRepository.findAll(guardExample); //FlatRepository.findById();
		return guardList;	
	}
    
	
	public List<GuardSalaryEntity> getAllGuardSalary(Integer pageNo, Integer pageSize, String sortBy)
	{
		logger.info("service Pager started");
	    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

	    Page<GuardSalaryEntity> pagedResult = guardSalaryRepository.findAll(paging);
	     
	    if(pagedResult.hasContent()) {
	        return pagedResult.getContent();
	    } else {
	        return new ArrayList<GuardSalaryEntity>();
	    }
	}

}