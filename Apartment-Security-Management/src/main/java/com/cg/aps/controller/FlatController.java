package com.cg.aps.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.cg.aps.entities.FlatEntity;
import com.cg.aps.exception.FlatIdNotFoundException;
import com.cg.aps.service.FlatService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/flats")     //http://localhost:4200/flat-management/flat/flats  //("/flats")
public class FlatController {

	Logger logger = LoggerFactory.getLogger(FlatController.class);

	@Autowired
	private FlatService flatService;

	// without entering FlatEntity flat
	@GetMapping
	public ResponseEntity<List<FlatEntity>> getFlat() {
		logger.info("Inside getFlat method");

		List<FlatEntity> flatList = flatService.getFlatEntity();
		// Creating an error response.

		ResponseEntity<List<FlatEntity>> response = new ResponseEntity<>(flatList, HttpStatus.NOT_FOUND);

		if (!flatList.isEmpty()) {
			logger.info("Getting all values");
			response = new ResponseEntity<>(flatList, HttpStatus.OK);
			
		} else {
			logger.error("No flats are found");
			throw new FlatIdNotFoundException(flatList + " Not Found");


		}
		return response;
	}

	// TO GET USING FLATENTITY FLAT
	@GetMapping("/flat")
	public ResponseEntity<List<FlatEntity>> getFlatByFlat(@Valid FlatEntity flat1) {
		System.out.println("********************" + flat1 + "****************");
		List<FlatEntity> flatList = flatService.getFlatByFlat(flat1);
		// Creating an error response.
		ResponseEntity<List<FlatEntity>> response = new ResponseEntity<>(flatList, HttpStatus.NOT_FOUND);
		if (!flatList.isEmpty()) {
			logger.info("Getting all values");
			response = new ResponseEntity<>(flatList, HttpStatus.OK);
		}else {
			logger.error("No flats are found");
			throw new FlatIdNotFoundException(" Not Found");


		}

		return response;
	}

	//
	@GetMapping(value = "/id/{userId}")
	public ResponseEntity<Object> getflat(@PathVariable("userId") int userId) {

		FlatEntity flat = flatService.getFlatById(userId);

		if (flat == null) {
			logger.error("No userId value found");
			throw new FlatIdNotFoundException("Flat Id "+ userId + " Not Found");

		} else {
			logger.info("Getting all values by ID");
		return new ResponseEntity<>(flat, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/name/{ownerName}")
	public ResponseEntity<Object> getFlatOwner(@PathVariable("ownerName") String ownerName) {

		FlatEntity flat1 = flatService.getFlatOwner(ownerName);

		if (flat1 == null) {
			logger.error("No ownerName found");
			throw new FlatIdNotFoundException("ownerName " + flat1 + " Not Found");

		}else {
			logger.info("Getting all values by ownerName");
		return new ResponseEntity<>(flat1, HttpStatus.OK);
	}
	}

	// INSERT USING POST
	@PostMapping
	public ResponseEntity<Object> addflat( @RequestBody FlatEntity flat2) {
		// If message is inserted it returns inserted flat object else null
		logger.info("****INSERT method****");
		FlatEntity newflat = flatService.insertFlat(flat2);
		// response is set to error if flat is null.
		if (newflat == null) {
			logger.error("Cannot be inserted");
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
			throw new FlatIdNotFoundException(newflat + " Not Found");

		} else {
			// response is set to inserted flat id in response header section.
//			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//					.buildAndExpand(newflat.getUserId()).toUri();
//			ResponseEntity.created(location).build();
			logger.info("New Flat INSERTED");
			return ResponseEntity.status(HttpStatus.OK).body(newflat);
		}

	}

	@DeleteMapping(value = "/delete/{userId}")
	public ResponseEntity<Object> deleteflat( @RequestBody @PathVariable("userId") int id) {
		
		logger.info("****DELETE method****");
		// If flat is deleted it returns deleted flat object else null
		FlatEntity flatPresent = flatService.deleteFlat(id);
		// Creating an success response.
		// response is set to error if flatPresent is null.
		if (flatPresent == null) {
			logger.error("cannot delete");
			throw new FlatIdNotFoundException(id + " Not Found");
		}else {
			logger.info(" Flat DELETED");
			return ResponseEntity.status(HttpStatus.OK).body("flat " + id + " deleted");
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Object> updateflat(  @RequestBody FlatEntity flat3) {

		logger.info("****UPDATE method****");
		// If flat is updated it returns updates flat object else null
		FlatEntity updateflat = flatService.updateFlat(flat3);
		// response is set to error if flat is null.
		if (updateflat == null) {
			logger.error("cannot update");
			throw new FlatIdNotFoundException(flat3 + " Not Found");
		} else {
			// response is set to updated id in response header section.
			logger.info(" Flat UPDATED");
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(flat3.getUserId()).toUri();
			return ResponseEntity.created(location).build();

		}
	}

	@GetMapping("/page")
	public ResponseEntity<List<FlatEntity>> 
					getPageFlats(@RequestParam(defaultValue = "0") Integer pageNo,
								@RequestParam(defaultValue = "10") Integer pageSize, 
								@RequestParam(defaultValue = "userId") String sortBy) {
		List<FlatEntity> list = flatService.getPageFlats(pageNo, pageSize, sortBy);
		if (list == null) {
			logger.error("No flats are found");
			throw new FlatIdNotFoundException(list + " Not Found");

		}else {
			logger.info("Getting all values");
		return new ResponseEntity<List<FlatEntity>>(list, new HttpHeaders(), HttpStatus.OK);
		}
	}

}
