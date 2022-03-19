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
import com.cg.aps.entities.GuardShiftEntity;
import com.cg.aps.entities.GuardTrainingEntity;
import com.cg.aps.repository.GuardShiftRepository;
import com.cg.aps.repository.GuardTrainingRepository;


@Service
public class GuardShiftService implements IGuardShiftService {
	@Autowired
	private GuardShiftRepository guardShiftRepository;
	Logger logger=LoggerFactory.getLogger(GuardTrainingController.class);
	
	public List getGuardShiftService()
	{
		List<GuardShiftEntity> guardexist=guardShiftRepository.findAll();
		return guardexist;
	}
	
	
	public GuardShiftEntity insertGuardShiftService(GuardShiftEntity guard)
	{
		logger.info("service insertguard started");
		List<GuardShiftEntity> guardexist=getGuardShiftEntity(guard.getUserId());
		if(guardexist==null)
		{
			guard=guardShiftRepository.save(guard);
		}
		return guard;
	}
	
	private List<GuardShiftEntity> getGuardShiftEntity(long l)
	{
		return null;
	}
	
	public GuardShiftEntity getGuardShiftById(long userId) {
		logger.info("service getGuard by ID started");
		Optional<GuardShiftEntity> guard = guardShiftRepository.findById(userId);
		return (guard.isPresent()) ? guard.get() : null;
	}
	
	public GuardShiftEntity deleteGuardShiftById(long userid) {
		logger.info("service deleteguard by ID started");
		GuardShiftEntity guard = getGuardShiftById(userid);
		if (guard != null)
			guardShiftRepository.deleteById(userid);
		return guard;
	}
	
	public GuardShiftEntity updateGuardShift(GuardShiftEntity guard) {
		logger.info("service updateguard started");
		GuardShiftEntity guardexist = getGuardShiftById(guard.getUserId());
		if (guardexist != null) {
			guard = guardShiftRepository.save(guard);
		}
		return guard;
	}
	
    public GuardShiftEntity getGuardShiftByName(String name) {
    	logger.info("service get Guard by Name started");
    	GuardShiftEntity guard = guardShiftRepository.name(name);
		return guard;
	}
	
	public List<GuardShiftEntity> getGuardShiftByGuard(GuardShiftEntity guard) {
		logger.info("service getGuard by Guard started");
		
//		FlatEntity employee = new FlatEntity();
//		 employee.setflat("Erika");
		 Example<GuardShiftEntity> guardExample = Example.of(guard);
		List<GuardShiftEntity> guardList = guardShiftRepository.findAll(guardExample); //FlatRepository.findById();
		return guardList;	
	}
	
	public List<GuardShiftEntity> getAllGuardShift(Integer pageNo, Integer pageSize, String sortBy)
	{
		logger.info("service Pager started");
	    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

	    Page<GuardShiftEntity> pagedResult = guardShiftRepository.findAll(paging);
	     
	    if(pagedResult.hasContent()) {
	        return pagedResult.getContent();
	    } else {
	        return new ArrayList<GuardShiftEntity>();
	    }
	}
	
	
	
	

}