package com.cg.aps.service;


import java.util.List;

import com.cg.aps.entities.VisitorEntity;

public interface IVisitorService {
	
	public List<VisitorEntity> getVisitorEntity();
	
	public List<VisitorEntity> getVisitorByVisit(VisitorEntity visit);
	
	public VisitorEntity getVisitorById(String flatNo);
	
	public VisitorEntity getVisitorOwner(String ownerName);
	
	public VisitorEntity insertVisitor(VisitorEntity visit);
	
	public VisitorEntity deleteVisitor(String flatNo);
	
	public VisitorEntity updateVisitor(VisitorEntity visit);
	
	public List<VisitorEntity> getAllVisits(Integer pageNo, Integer pageSize, String sortBy);

}