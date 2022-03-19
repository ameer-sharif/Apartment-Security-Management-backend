package com.cg.aps.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.cg.aps.controller.GuardTrainingController;
import com.cg.aps.entities.GuardTrainingEntity;
import com.cg.aps.repository.GuardTrainingRepository;
import java.util.Optional;

@Service
public class GuardTrainingService implements IGuardTrainingService{
	
	@Autowired
	private GuardTrainingRepository guardTrainingRepository;
	Logger logger=LoggerFactory.getLogger(GuardTrainingController.class);
	
	@Override
	public List getGuardTrainingEntity()
	{
		List<GuardTrainingEntity> messageexist=guardTrainingRepository.findAll();
		return messageexist;
	}
	
	@Override
	public GuardTrainingEntity insertGuardTrainingEntity(GuardTrainingEntity guard)
	{
		logger.info("service insertguard started");
		List<GuardTrainingEntity> guardexist=getGuardTrainingEntity(guard.getUserId());
		if(guardexist==null)
		{
			guard=guardTrainingRepository.save(guard);
		}
		return guard;
	}
	
	@Override
	public List<GuardTrainingEntity> getGuardTrainingEntity(long l)
	{
		return null;
	}
	
	@Override
	public GuardTrainingEntity deleteGuardById(long userid) {
		logger.info("service deleteguard started");
		GuardTrainingEntity guard = getGuardById(userid);
		if (guard != null)
			guardTrainingRepository.deleteById(userid);
		return guard;
	}
	
	@Override
	public GuardTrainingEntity getGuardById(long userId) {
		logger.info("service getguardBy ID started");
		Optional<GuardTrainingEntity> guard = guardTrainingRepository.findById(userId);
		return (guard.isPresent()) ? guard.get() : null;
	}
	
	
	@Override
	public GuardTrainingEntity updateGuard(GuardTrainingEntity guard) {
		logger.info("service updateGuard ID started");
		GuardTrainingEntity guardexist = getGuardById(guard.getUserId());
		if (guardexist != null) {
			guard = guardTrainingRepository.save(guard);
		}
		return guard;
	}

	@Override
    public GuardTrainingEntity getGuardName(String name) {
		logger.info("service getguardBy Name started");
		GuardTrainingEntity guard = guardTrainingRepository.name(name);
		return guard;
	}
	
	@Override
	public List<GuardTrainingEntity> getGuardByGuard(GuardTrainingEntity guard) {
		logger.info("service getguardBy Guard started");
//		FlatEntity employee = new FlatEntity();
//		 employee.setflat("Erika");
		 Example<GuardTrainingEntity> guardExample = Example.of(guard);
		List<GuardTrainingEntity> guardList = guardTrainingRepository.findAll(guardExample); //FlatRepository.findById();
		return guardList;	
	}
	
	@Override
	public List<GuardTrainingEntity> getAllGuardTraining(Integer pageNo, Integer pageSize, String sortBy)
	{
		logger.info("service Pager started");
	    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

	    Page<GuardTrainingEntity> pagedResult = guardTrainingRepository.findAll(paging);
	     
	    if(pagedResult.hasContent()) {
	        return pagedResult.getContent();
	    } else {
	        return new ArrayList<GuardTrainingEntity>();
	    }
	}
//	
}