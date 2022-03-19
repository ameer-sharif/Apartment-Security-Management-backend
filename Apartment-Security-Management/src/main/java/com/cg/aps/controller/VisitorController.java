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


import com.cg.aps.entities.VisitorEntity;
//import com.cg.aps.exception.FlatIdNotFoundException;
import com.cg.aps.service.VisitorService;
import com.cg.aps.exception.*;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/visitors")

public class VisitorController {
	
	@Autowired
	private VisitorService visitorService;
	Logger logger=LoggerFactory.getLogger(VisitorController.class);
	
	//without entering VisitorEntity flat
	
	@GetMapping
	public ResponseEntity<List<VisitorEntity>> getVisitor() {
		logger.info("Inside GetVisitor method");
		List<VisitorEntity> visitorList = visitorService.getVisitorEntity();
		ResponseEntity<List<VisitorEntity>> response = new ResponseEntity<>(visitorList, HttpStatus.INTERNAL_SERVER_ERROR);
		
		if (!visitorList.isEmpty()) {
			response = new ResponseEntity<>(visitorList, HttpStatus.OK);
		}
		return response;
	}
	
	//TO POST AND GET USING VisitorEntity FLAT
		@GetMapping("/visit")
		 public ResponseEntity<List<VisitorEntity>> getVisitorByVisit(VisitorEntity visit ) {
			logger.info("Inside Get vsisitor by visitor");
			System.out.println("**"+visit+"**");
			List<VisitorEntity> visitorList = visitorService.getVisitorByVisit(visit);
			// Creating an error response.
			ResponseEntity<List<VisitorEntity>> response = new ResponseEntity<>(visitorList, HttpStatus.NOT_FOUND);
			if (!visitorList.isEmpty()) {
				response = new ResponseEntity<>(visitorList, HttpStatus.OK);
			}

			return response;
		}
	
	@GetMapping(value = "/id/{flatNo}")
	public ResponseEntity<Object> getVisitor(@PathVariable("flatNo") String flatNo) {
		logger.info("Inside get visitor by id");

		VisitorEntity visitor = visitorService.getVisitorById(flatNo);

		if (visitor == null) {
			logger.error("flat Id can not be null");
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new UserIdNotFoundException( flatNo+ " Not Found");

		}
		logger.info("visitor entity foound");
		return new ResponseEntity<>(visitor, HttpStatus.OK);
	}
	
	@GetMapping(value = "/name/{ownerName}")
	public ResponseEntity<Object> getVisitorOwner(@PathVariable("ownerName") String ownerName){
		logger.info("Inside get visitor by name method");

		VisitorEntity visitor = visitorService.getVisitorOwner(ownerName);

		if (visitor == null) {
			logger.error("name not found" );
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new UserIdNotFoundException(ownerName + " Not Found");

		}
		logger.info("Name found");
		return new ResponseEntity<>(visitor, HttpStatus.OK);
	}
	
	
	//INSERT USING POST
	
	@PostMapping
	public  ResponseEntity<Object> addVisitor(@RequestBody VisitorEntity visit) {
		logger.info("Inside Post method" + visit);
		System.out.println("post method is calling********" +visit);
		
		VisitorEntity newVisitor = visitorService.insertVisitor(visit);
		
		if (newVisitor == null) 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
		logger.error("visitor details Added");
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newVisitor.getFlatNo()).toUri();
		
		return ResponseEntity.created(location).build();
	
	}

	
	@DeleteMapping(value = "/{flatNo}")
	public ResponseEntity<Object> deleteVisitor(@PathVariable("flatNo") String flatNo) {
		logger.info("Inside delete method");
		VisitorEntity visitorPresent = visitorService.deleteVisitor(flatNo);
		if (visitorPresent == null) {
			logger.error("Invalid flat id");
			throw new UserIdNotFoundException(flatNo + " Not Found");
		}
		logger.info("Visitor deleted");
		return ResponseEntity.status(HttpStatus.OK).body("visitor " + flatNo+ " deleted");
	}

	
	@PutMapping
	public ResponseEntity<Object> updateVisitor( @RequestBody VisitorEntity visit) {
		logger.info("Inside update method******");
		System.out.println("*******put method is calling*******" +visit);
		
		VisitorEntity updateVisitor = visitorService.updateVisitor(visit);
		
		System.out.println("*******post method is calling*******" +updateVisitor);
		if (updateVisitor == null) {
			logger.error("flat id not found");
		
			throw new UserIdNotFoundException(visit + " Not Found");
		} else {
			logger.info("visitor updated");
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(visit.getFlatNo()).toUri();
//			return new ResponseEntity<>(updateVisitor, HttpStatus.OK);
			return ResponseEntity.created(location).build();

		}
	}
	
	@GetMapping("/page")
    public ResponseEntity<List<VisitorEntity>> getAllPages(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "userId") String sortBy) 
    {
		logger.info("inside get visitor by page method");
        List<VisitorEntity> list = visitorService.getAllVisits(pageNo, pageSize, sortBy);
        if (list == null) {
        	logger.error("Invalid page no,page size,field name");
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new UserIdNotFoundException(list + " Not Found");

		}
        logger.info("visitor fetched by page");		
        return new ResponseEntity<List<VisitorEntity>> (list, new HttpHeaders(), HttpStatus.OK); 
    }
	
	

}