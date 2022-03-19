package com.cg.aps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cg.aps.entities.FlatRentEntity;
import com.cg.aps.entities.FlatRentEntity;
import com.cg.aps.repository.FlatRentRepository;

@Service
public class FlatRentService implements IFlatRentService  {
	
	@Autowired
	private FlatRentRepository flatRentRepository;
	
	@Override
	public List<FlatRentEntity> getFlatRentEntity() {
		List<FlatRentEntity> rentList = flatRentRepository.findAll();
		return rentList;
	}
	
	@Override
	public List<FlatRentEntity> getRentByRent(FlatRentEntity rent1) {
		
		 Example<FlatRentEntity> employeeExample = Example.of(rent1);
		List<FlatRentEntity> rentList = flatRentRepository.findAll(employeeExample); //flatRentRepository.findById();
		return rentList;	
	}
	
	@Override
	public FlatRentEntity getFlatRentById(int id) {
		Optional<FlatRentEntity> rent = flatRentRepository.findById(id);
		return (rent.isPresent()) ? rent.get() : null;
	}
	
	@Override
	public FlatRentEntity getFlatRentByOwnerName(String ownerName) {
		FlatRentEntity rent1 = flatRentRepository.ownerName(ownerName);
		return rent1;
	}
		
	@Override
	public FlatRentEntity insertFlatRentEntity(FlatRentEntity rent) {
		FlatRentEntity rentexist = getFlatRentById(rent.getUserId());
		if (rentexist == null) {
			rent = flatRentRepository.save(rent);
		}
		return rent;
	}
	
	@Override
	public FlatRentEntity deleteFlatRentEntity(int id) {
		FlatRentEntity rent = getFlatRentById(id);
		if (rent != null)
			flatRentRepository.deleteById(id);
		return rent;
	}

	@Override
	public FlatRentEntity updateFlatRentEntity(FlatRentEntity rent) {
		FlatRentEntity rentexist = getFlatRentById(rent.getUserId());
		if (rentexist != null) {
			rent = flatRentRepository.save(rent);
		}
		return rent;
	}

	@Override
	public List<FlatRentEntity> getPageRents(Integer pageNo, Integer pageSize, String sortBy)
	{
	    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

	    Page<FlatRentEntity> pagedResult = flatRentRepository.findAll(paging);
	     
	    if(pagedResult.hasContent()) {
	        return pagedResult.getContent();
	    } else {
	        return new ArrayList<FlatRentEntity>();
	    }
	}
	
	
	
	

}
