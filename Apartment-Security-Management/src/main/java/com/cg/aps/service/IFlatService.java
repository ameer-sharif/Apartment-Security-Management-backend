package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.FlatEntity;

public interface IFlatService {
	
	public List<FlatEntity> getFlatEntity();
	
	public List<FlatEntity> getFlatByFlat(FlatEntity flat1);
	
	public FlatEntity getFlatById(int id);
	
	public FlatEntity getFlatOwner(String ownerName);
	
	public FlatEntity insertFlat(FlatEntity flat2);
	
	public FlatEntity deleteFlat(int id);
	
	public FlatEntity updateFlat(FlatEntity flat3);
	
	public List<FlatEntity> getPageFlats(Integer pageNo, Integer pageSize, String sortBy);


}
