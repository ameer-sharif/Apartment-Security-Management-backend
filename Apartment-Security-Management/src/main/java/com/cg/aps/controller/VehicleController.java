package com.cg.aps.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.aps.entities.VehicleEntity;
import com.cg.aps.exception.VehicleIdNotFoundException;
import com.cg.aps.service.VehicleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/vehicle")
	public class VehicleController {
		
	@Autowired
	private VehicleService VehicleService;
	
	Logger logger = LoggerFactory.getLogger(VehicleService.class);
	
	//**get all the value by findall**
	@GetMapping
	public ResponseEntity<List<VehicleEntity>> getVehicleEntity() {
		logger.info("Searching.......");

		List<VehicleEntity> vehicleList = VehicleService.getVehicleEntity();
		ResponseEntity<List<VehicleEntity>> response = new ResponseEntity<>(vehicleList, HttpStatus.NOT_FOUND);

		if (!vehicleList.isEmpty()) {
			
			logger.info("Searched value found");
			response = new ResponseEntity<>(vehicleList, HttpStatus.OK);
		}

		return response;
	}
	
	//**get vehicle details by findname**
	@GetMapping(value = "/{name}")
	public ResponseEntity<Object> getvehi(@PathVariable("name") String name) {
		logger.info("Searching by name");

		VehicleEntity vehi = VehicleService.getVehicleEntity(name);
		if (name == null) {
			logger.error("vehicle by name is not found");
		throw new VehicleIdNotFoundException(name + " Not Found");
			}
		else {
		logger.info("vehicle by name found");

		return new ResponseEntity<>(vehi, HttpStatus.OK);
	}
	}
	//**To post and get using deliveryentity**
	
	@GetMapping("/vehicle")
	 public ResponseEntity<List<VehicleEntity>> getVehicleByVehicle(VehicleEntity vehicle1) {
		logger.info("vehicle is searching.....");
		System.out.println("**"+vehicle1+"**");
		List<VehicleEntity> vehicleList = VehicleService.getVehicleByVehicle(vehicle1);
		// Creating an error response.
		ResponseEntity<List<VehicleEntity>> response = new ResponseEntity<>(vehicleList, HttpStatus.NOT_FOUND);
		if (!vehicleList.isEmpty()) {
			logger.info("vehicle found");
			response = new ResponseEntity<>(vehicleList, HttpStatus.OK);
		}

		return response;
	}
	//**get all the value by giving name**
	@GetMapping(value = "/name")
	public ResponseEntity<Object> getvehicleName(@PathVariable("name") String name) throws Exception {
		logger.info("vehicle is searching by name...");

		VehicleEntity vehicle1 = VehicleService.getVehicleOwner(name);
		if (vehicle1 == null) {
			logger.error("vehicle is not found by name");
			throw new Exception(name + " Not Found");

		}
		logger.info("vehicle is found by name");

		return new ResponseEntity<>(name, HttpStatus.OK);
	}
	
	//**inserting the value**
	@PostMapping
	public ResponseEntity<Object> addvehi(@RequestBody VehicleEntity vehi) {
		logger.info("adding new vehicle...");
		
		VehicleEntity newMessage = VehicleService.insertVehicleEntity(vehi);
		
		if (newMessage == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newMessage.getName()).toUri();
		
		logger.info("vehicle is added");
		return ResponseEntity.created(location).build();
	}

	//**deleting the values**
	@DeleteMapping(value = "/{name}")
	public ResponseEntity<Object> deleteVehi(@PathVariable("name") String name) {
		
		logger.info("vehicle is deleting...");

		VehicleEntity vehiPresent = VehicleService.deleteVehicleEntity(name);
		
		if (vehiPresent == null) {
			logger.info("vehicle is deleted");
			
	throw new VehicleIdNotFoundException(name + " Not Found");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("vehi " + name + " deleted");
	}
	//**updating the value**
	@PutMapping
	public ResponseEntity<Object> updateProduct(
			@RequestBody VehicleEntity vehi) {
		
		logger.info("vehicle is updating");
		
		VehicleEntity updatevehi = VehicleService.updateVehicleEntity(vehi);
		
		if (updatevehi == null) {
			logger.error("vehicle is not updated");

		throw new VehicleIdNotFoundException(vehi + " Not Found");
		} else {
	
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}")
					.buildAndExpand(vehi.getName()).toUri();
			return ResponseEntity.created(location).build();
		}
	}
	//**pagination**
		@GetMapping("/page")
	    public ResponseEntity<List<VehicleEntity>> getAllVehicles(
	                        @RequestParam(defaultValue = "0") Integer pageNo, 
	                        @RequestParam(defaultValue = "10") Integer pageSize,
	                        @RequestParam(defaultValue = "userId") String sortBy) 
	    {
			
			logger.info("pagination starting....");
	        List<VehicleEntity> list = VehicleService.getAllVehicles(pageNo, pageSize, sortBy);
	        if (list == null) {
	        	logger.error("page not found");
				throw new VehicleIdNotFoundException(list + " Not Found");

			}
	        logger.info("showing list of values in pagr");

			
	        return new ResponseEntity<List<VehicleEntity>> (list, new HttpHeaders(), HttpStatus.OK); 
	    }
		
	}