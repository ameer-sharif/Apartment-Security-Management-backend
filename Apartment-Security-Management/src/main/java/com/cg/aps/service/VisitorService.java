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

import com.cg.aps.entities.VisitorEntity;
import com.cg.aps.repository.VisitorRepository;



@Service
public class VisitorService implements IVisitorService {
	
	@Autowired
	private VisitorRepository visitRep;
	
	//without passing VisitorEntity flat
	@Override
	public List<VisitorEntity> getVisitorEntity() {
	//	logger.info("Inside visitor entity");
		List<VisitorEntity> visitList = visitRep.findAll();
		return visitList;
	}
	@Override
	public List<VisitorEntity> getVisitorByVisit(VisitorEntity visit) {
		
		 Example<VisitorEntity> employeeExample = Example.of(visit);
		List<VisitorEntity> visitorList = visitRep.findAll(employeeExample); //visitorRepository.findById();
		return visitorList;	
	}
	@Override
	public VisitorEntity getVisitorById(String flatNo) {
		Optional<VisitorEntity> visitor = visitRep.findById(flatNo);
		return (visitor.isPresent()) ? visitor.get() : null;
		
	}
	@Override
	public VisitorEntity getVisitorOwner(String ownerName) {
		VisitorEntity visitor = visitRep.ownerName(ownerName);
		return visitor;
	}
	
	@Override
	public VisitorEntity insertVisitor(VisitorEntity visit) {
		
		VisitorEntity visitorexist = getVisitorById(visit.getFlatNo());
		System.out.println( visitorexist);
		if (visitorexist == null) {
			visit = visitRep.save(visit);
			
		}
		return visit;
	}
	
	@Override
	public VisitorEntity deleteVisitor(String flatNo) {
		VisitorEntity visitor = getVisitorById(flatNo);
		if (visitor != null)
			visitRep.deleteById(flatNo);
		return visitor;
	}

	@Override
	public VisitorEntity updateVisitor(VisitorEntity visit) {
		VisitorEntity visitorexist = getVisitorById(visit.getFlatNo());
		if (visitorexist != null) {
			visit = visitRep.save(visit);
		}
		return visit;
	}
	@Override
	public List<VisitorEntity> getAllVisits(Integer pageNo, Integer pageSize, String sortBy)
	{
	    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

	    Page<VisitorEntity> pagedResult = visitRep.findAll(paging);
	     
	    if(pagedResult.hasContent()) {
	        return pagedResult.getContent();
	    } else {
	        return new ArrayList<VisitorEntity>();
	    }
	}
}
	

//	public VisitorEntity getVisitorEntityPagination() {
//		VisitorEntity flatList = visitorRepository.find();
//		return flatList;
//	}