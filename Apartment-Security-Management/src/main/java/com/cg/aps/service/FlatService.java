package com.cg.aps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cg.aps.controller.FlatController;
import com.cg.aps.entities.FlatEntity;
import com.cg.aps.repository.FlatRepository;

@Service
public class FlatService implements IFlatService{
	
	Logger logger = LoggerFactory.getLogger(FlatService.class);

	@Autowired
	private FlatRepository flatRepository;

	@Override
	public List<FlatEntity> getFlatEntity() {
		logger.info("Inside getFlatService method");
		List<FlatEntity> flatList = flatRepository.findAll();
		return flatList;
	}

	@Override
	public List<FlatEntity> getFlatByFlat(FlatEntity flat1) {
		logger.info("Inside getFlatService method");
		Example<FlatEntity> employeeExample = Example.of(flat1);
		List<FlatEntity> flatList = flatRepository.findAll(employeeExample); // flatRepository.findById();
		return flatList;
	}

	@Override
	public FlatEntity getFlatById(int id) {
		logger.info("Inside getFlatService method");
		Optional<FlatEntity> flat = flatRepository.findById(id);
		return (flat.isPresent()) ? flat.get() : null;
	}

	@Override
	public FlatEntity getFlatOwner(String ownerName) {
		logger.info("Inside getFlatService method");
		FlatEntity flat1 = flatRepository.ownerName(ownerName);
		return flat1;
	}

	@Override
	public FlatEntity insertFlat(FlatEntity flat2) {
		logger.info("Inside getFlatService method");
		FlatEntity flatexist = getFlatById(flat2.getUserId());
		if (flatexist == null) {
			flat2 = flatRepository.save(flat2);
		}
		return flat2;
	}

	@Override
	public FlatEntity deleteFlat(int id) {
		logger.info("Inside getFlatService method");
		FlatEntity flat = getFlatById(id);
		if (flat != null)
			flatRepository.deleteById(id);
		return flat;
	}

	@Override
	public FlatEntity updateFlat(FlatEntity flat3) {
		logger.info("Inside getFlatService method");
		FlatEntity flatexist = getFlatById(flat3.getUserId());
		if (flatexist != null) {
			flat3 = flatRepository.save(flat3);
		}
		return flat3;
	}

	@Override
	public List<FlatEntity> getPageFlats(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		logger.info("Inside getFlatService method");
		Page<FlatEntity> pagedResult = flatRepository.findAll(paging);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<FlatEntity>();
		}
	}
}


