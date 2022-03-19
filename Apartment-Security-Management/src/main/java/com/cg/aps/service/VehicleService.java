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

import com.cg.aps.entities.VehicleEntity;
import com.cg.aps.repository.VehicleRepository;

@Service
public class VehicleService implements IVehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Override
	public List<VehicleEntity> getVehicleEntity() {
		List<VehicleEntity> vehiList = vehicleRepository.findAll();
		return vehiList;
	}
	
	@Override
	public VehicleEntity getVehicleEntity(String name) {
		Optional<VehicleEntity> vehi = vehicleRepository.findById(name);
		return (vehi.isPresent()) ? vehi.get() : null;
	}
	
	@Override
	public VehicleEntity insertVehicleEntity(VehicleEntity vehi) {
		VehicleEntity vehiexist = getVehicleEntity(vehi.getName());
		if (vehiexist == null) {
			vehi = vehicleRepository.save(vehi);
		}
		return vehi;
	}
	
	@Override
	public VehicleEntity deleteVehicleEntity(String name) {
		VehicleEntity vehi = getVehicleEntity(name);
		if (vehi != null)
			vehicleRepository.deleteById(name);
		return vehi;
	}

	@Override
	public VehicleEntity updateVehicleEntity(VehicleEntity vehi) {
		VehicleEntity vehiexist = getVehicleEntity(vehi.getName());
		if (vehiexist != null) {
			vehi = vehicleRepository.save(vehi);
		}
		return vehi;
	}
	
	@Override
   public List<VehicleEntity> getVehicleByVehicle(VehicleEntity vehicle1) {
	
		 Example<VehicleEntity> employeeExample = Example.of(vehicle1);
		List<VehicleEntity> vehicleList = vehicleRepository.findAll(employeeExample); //FlatRepository.findById();
		return vehicleList;	
	}
	@Override
   public VehicleEntity getVehicleOwner(String name) {
		VehicleEntity vehicle1 = vehicleRepository.name(name);
		return vehicle1;
	}
	
	@Override
   public List<VehicleEntity> getAllVehicles(Integer pageNo, Integer pageSize, String sortBy)
   {
       Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

       Page<VehicleEntity> pagedResult = vehicleRepository.findAll(paging);
        
       if(pagedResult.hasContent()) {
           return pagedResult.getContent();
       } else {
           return new ArrayList<VehicleEntity>();
       }
   }
   
}