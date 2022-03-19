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

import com.cg.aps.entities.GuardSalaryEntity;
import com.cg.aps.exception.GuardSalaryNameNotFoundException;
import com.cg.aps.exception.GuardSalaryUserIdNotFoundException;

import com.cg.aps.service.GuardSalaryService;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/GuardSalary")
public class GuardSalaryController {
	
	@Autowired
	private GuardSalaryService guardSalaryService;
	Logger logger=LoggerFactory.getLogger(GuardTrainingController.class);
	
	@GetMapping
	public ResponseEntity<List<GuardSalaryEntity>> getGuardSalaryEntity() {
		logger.info("Get All users Method");

		List<GuardSalaryEntity> guardList = guardSalaryService.getGuardSalaryEntity();
		// Creating an error response.
		ResponseEntity<List<GuardSalaryEntity>> response = new ResponseEntity<>(guardList, HttpStatus.NOT_FOUND);

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
	public ResponseEntity<Object> addGuardSalaryReq(@RequestBody GuardSalaryEntity guard) {
		logger.info("addguard Method called");
		// If message is inserted it returns inserted message object else null
		GuardSalaryEntity newGuard = guardSalaryService.insertGuardSalaryEntity(guard);
		// response is set to error if message is null.
		if (newGuard == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
		// response is set to inserted message id in response header section.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newGuard.getUserId()).toUri();
		logger.info("Guard added successfully");
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping(value = "/delete/{userId}")
	public ResponseEntity<Object> deleteGuardSalaryReq(@PathVariable("userId") long userid) {
		logger.info("Delete guard method");
		// If message is deleted it returns deleted message object else null
		GuardSalaryEntity guardPresent = guardSalaryService.deleteGuardSalaryById(userid);
		
		// Creating an success response.
		// ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK)
		// .body("Message " + messageId + " deleted");
		// response is set to error if message is null.
		if (guardPresent == null) {
			// response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message " +
			// messageId + " Not found");
		    throw new GuardSalaryUserIdNotFoundException(userid + " Not Found");
		}
		// return response;
		logger.info("Deleted Guard Sucessfully");
		return ResponseEntity.status(HttpStatus.OK).body("Message " + userid + " deleted");
	}
	

	@PutMapping("/update")
	public ResponseEntity<Object> updateGuardSalaryReq(@RequestBody GuardSalaryEntity guard) {
		logger.info("Update Guard method");
		GuardSalaryEntity updateGuard = guardSalaryService.updateGuardSalary(guard);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
					.buildAndExpand(guard.getUserId()).toUri();
			logger.info("Updated Guard Successfully");
			return ResponseEntity.created(location).build();
		}
	
	@GetMapping(value = "/userId/{userId}")
	public ResponseEntity<Object> getGuardSalaryByIdReq(@PathVariable("userId") long userId) {
		logger.info("Get GuardById method");

		GuardSalaryEntity guard = guardSalaryService.getGuardSalaryById(userId);

		// Creating an error response.
		// ResponseEntity<Object> response =
		// ResponseEntity.status(HttpStatus.BAD_REQUEST)
		// .body("Message " + messageId + " Not found");

		// If message is not null it sets the message object in response else by default
		// error
		// will be there in response.
		if (guard == null) {
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new GuardSalaryUserIdNotFoundException(userId + " Not Found");

		}
		logger.info("Guard Retrieved Successfully");

		return new ResponseEntity<>(guard, HttpStatus.OK);
	}
	
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getGuardSalaryByNameReq(@PathVariable("name") String name) {
		logger.info("Update Guard by name method");

		GuardSalaryEntity guard = guardSalaryService.getGuardSalaryByName(name);

		// Creating an error response.
		// ResponseEntity<Object> response =
		// ResponseEntity.status(HttpStatus.BAD_REQUEST)
		// .body("Message " + messageId + " Not found");

		// If message is not null it sets the message object in response else by default
		// error
		// will be there in response.
		if (guard == null) {
			//response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new GuardSalaryNameNotFoundException(name + " Not Found");

		}
		logger.info("Retrieved Guard Successfully");

		return new ResponseEntity<>(guard, HttpStatus.OK);
	}
	
	@GetMapping("/guardsalary")
	 public ResponseEntity<List<GuardSalaryEntity>> getGuardSalaryByGuard(GuardSalaryEntity guard1 ) {
		logger.info("Get Guard By Guard Method");
		List<GuardSalaryEntity> guardList = guardSalaryService.getGuardSalaryByGuard(guard1);
		// Creating an error response.
		ResponseEntity<List<GuardSalaryEntity>> response = new ResponseEntity<>(guardList, HttpStatus.NOT_FOUND);
		if (!guardList.isEmpty()) {
			response = new ResponseEntity<>(guardList, HttpStatus.OK);
		}
		
		logger.info("Retrieved Guard Successfully");

		return response;
	}
	
	
	@GetMapping("/page")
    public ResponseEntity<List<GuardSalaryEntity>> getAllGuardTrainingReq(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "userId") String sortBy) 
    {
		logger.info("Pager Method");
        List<GuardSalaryEntity> guardList = guardSalaryService.getAllGuardSalary(pageNo, pageSize, sortBy);
        if (guardList == null) {
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new GuardSalaryUserIdNotFoundException(guardList + " Not Found");

		}
        logger.info("Retrieved Pager Successfully");
        return new ResponseEntity<List<GuardSalaryEntity>> (guardList, new HttpHeaders(), HttpStatus.OK); 
    }

	}