package com.cg.aps.controller;

import java.net.URI;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;

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

import com.cg.aps.entities.GuardShiftEntity;
import com.cg.aps.entities.GuardTrainingEntity;
import com.cg.aps.exception.GuardShiftNameNotFoundException;
import com.cg.aps.exception.GuardShiftUserIdNotFoundException;
import com.cg.aps.exception.GuardTrainingNameNotFoundException;
import com.cg.aps.exception.GuardTrainingUserIdNotFoundException;
import com.cg.aps.service.GuardShiftService;
import com.cg.aps.service.GuardTrainingService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/GuardShift")

public class GuardShiftController {
	
	@Autowired
	private GuardShiftService guardShiftService;
	Logger logger=LoggerFactory.getLogger(GuardTrainingController.class);
	

	@GetMapping
	public ResponseEntity<List<GuardShiftEntity>> getGuardTrainingEntity() {
		
		logger.info("Get All users Method");
		

		List<GuardShiftEntity> guardList = guardShiftService.getGuardShiftService();
		// Creating an error response.
		ResponseEntity<List<GuardShiftEntity>> response = new ResponseEntity<>(guardList, HttpStatus.NOT_FOUND);

		// If messageList is not empty it sets the list in response else by default
		// error will be
		// there in response.
		if (!guardList.isEmpty()) {
			response = new ResponseEntity<>(guardList, HttpStatus.OK);
			logger.info("All users Retrieved Successfully");
		}

		return response;
	}

	
	@PostMapping
	public ResponseEntity<Object> addGuardShiftReq(@RequestBody GuardShiftEntity guard) {
		// If message is inserted it returns inserted message object else null
		logger.info("addguard Method called");
		GuardShiftEntity newGuard = guardShiftService.insertGuardShiftService(guard);
		// response is set to error if message is null.
		if (newGuard == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
		// response is set to inserted message id in response header section.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newGuard.getUserId()).toUri();
		logger.info("added user sucessfully");
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping(value = "/delete/{userId}")
	public ResponseEntity<Object> deleteGuardShiftReq(@PathVariable("userId") long userid) {
		logger.info("delete user method");
		// If message is deleted it returns deleted message object else null
		GuardShiftEntity guardPresent = guardShiftService.deleteGuardShiftById(userid);
		
		// Creating an success response.
		// ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK)
		// .body("Message " + messageId + " deleted");
		// response is set to error if message is null.
		if (guardPresent == null) {
			//response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message " +
			// messageId + " Not found");
		    throw new GuardShiftUserIdNotFoundException(userid + " Not Found");
	       }
		// return response;
		logger.info("deleted user sucessfully");
		return ResponseEntity.status(HttpStatus.OK).body("Message " + userid + " deleted");
	}
	

	@PutMapping("/update")
	public ResponseEntity<Object> updateGuardShiftReq(@RequestBody GuardShiftEntity guard) {
		GuardShiftEntity updateMessage = guardShiftService.updateGuardShift(guard);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(guard.getUserId()).toUri();
			return ResponseEntity.created(location).build();
		}
	
	
	@GetMapping(value = "/userId/{userId}")
	public ResponseEntity<Object> getGuardShiftByIdReq(@PathVariable("userId") long userId) {

		GuardShiftEntity guard = guardShiftService.getGuardShiftById(userId);

		// Creating an error response.
		// ResponseEntity<Object> response =
		// ResponseEntity.status(HttpStatus.BAD_REQUEST)
		// .body("Message " + messageId + " Not found");

		// If message is not null it sets the message object in response else by default
		// error
		// will be there in response.
		if (guard == null) {
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new GuardShiftUserIdNotFoundException(userId + " Not Found");

		}

		return new ResponseEntity<>(guard, HttpStatus.OK);
	}
	
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getGuardShiftByNameReq(@PathVariable("name") String name){

		GuardShiftEntity guard = guardShiftService.getGuardShiftByName(name);

		// Creating an error response.
		// ResponseEntity<Object> response =
		// ResponseEntity.status(HttpStatus.BAD_REQUEST)
		// .body("Message " + messageId + " Not found");

		// If message is not null it sets the message object in response else by default
		// error
		// will be there in response.
		if (guard == null) {
			//response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new GuardShiftNameNotFoundException(name + " Not Found");

		}

		return new ResponseEntity<>(guard, HttpStatus.OK);
	}
	
	@GetMapping("/guardshift")
	 public ResponseEntity<List<GuardShiftEntity>> getGuardShiftByGuardReq(GuardShiftEntity guard1 ) {
		System.out.println("**"+guard1+"**");
		List<GuardShiftEntity> guardList = guardShiftService.getGuardShiftByGuard(guard1);
		// Creating an error response.
		ResponseEntity<List<GuardShiftEntity>> response = new ResponseEntity<>(guardList, HttpStatus.NOT_FOUND);
		if (!guardList.isEmpty()) {
			response = new ResponseEntity<>(guardList, HttpStatus.OK);
		}

		return response;
	}
	
	@GetMapping("/page")
    public ResponseEntity<List<GuardShiftEntity>> getAllGuardTrainingReq(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "userId") String sortBy) 
    {
        List<GuardShiftEntity> guardList = guardShiftService.getAllGuardShift(pageNo, pageSize, sortBy);
        if (guardList == null) {
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new GuardShiftUserIdNotFoundException(guardList + " Not Found");

		}		
        return new ResponseEntity<List<GuardShiftEntity>> (guardList, new HttpHeaders(), HttpStatus.OK); 
    }
	

	
//GuardShiftEntity
}