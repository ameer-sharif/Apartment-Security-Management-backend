package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.VehicleEntity;

public interface IVehicleService {
	public List<VehicleEntity> getVehicleEntity();
	
	public VehicleEntity getVehicleEntity(String name);
	
	public VehicleEntity insertVehicleEntity(VehicleEntity vehi);
	
	public VehicleEntity deleteVehicleEntity(String name);
	
	public VehicleEntity updateVehicleEntity(VehicleEntity vehi);
	
	public List<VehicleEntity> getVehicleByVehicle(VehicleEntity vehicle1);
	
	public VehicleEntity getVehicleOwner(String name);
	
	public List<VehicleEntity> getAllVehicles(Integer pageNo, Integer pageSize, String sortBy);

}