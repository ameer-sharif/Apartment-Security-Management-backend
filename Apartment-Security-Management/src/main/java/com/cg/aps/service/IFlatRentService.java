package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.FlatRentEntity;

public interface IFlatRentService {

	public List<FlatRentEntity> getFlatRentEntity();
	
	public List<FlatRentEntity> getRentByRent(FlatRentEntity rent1);
	
	public FlatRentEntity getFlatRentById(int id);
	
	public FlatRentEntity getFlatRentByOwnerName(String ownerName);
	
	public FlatRentEntity insertFlatRentEntity(FlatRentEntity rent);
	
	public FlatRentEntity deleteFlatRentEntity(int id);
	
	public FlatRentEntity updateFlatRentEntity(FlatRentEntity rent);
	
	public List<FlatRentEntity> getPageRents(Integer pageNo, Integer pageSize, String sortBy);

	

	
}
