package com.cg.aps.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.cg.aps.entities.FlatRentEntity;
import com.cg.aps.exception.FlatIdNotFoundException;
import com.cg.aps.exception.RentIdNotFoundException;
import com.cg.aps.service.FlatRentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/flatRent")
public class FlatRentController {
	
	@Autowired
	private FlatRentService flatRentService;
	
	
	@GetMapping
	public ResponseEntity<List<FlatRentEntity>> getFlatRentEntity() {

		List<FlatRentEntity> rentList = flatRentService.getFlatRentEntity();
		// Creating an error response.
		ResponseEntity<List<FlatRentEntity>> response = new ResponseEntity<>(rentList, HttpStatus.NOT_FOUND);

		// If rentList is not empty it sets the list in response else by default
		// error will be
		// there in response.
		if (!rentList.isEmpty()) {
			response = new ResponseEntity<>(rentList, HttpStatus.OK);
		}

		return response;
	}
	
	
	//TO POST AND GET USING FlatRentEntity FLAT
	@GetMapping("/rent")
	public ResponseEntity<List<FlatRentEntity>> getRentByRent(@Valid FlatRentEntity rent1 ) {
		
		System.out.println("********************"+rent1+"****************");
		List<FlatRentEntity> rentList = flatRentService.getRentByRent(rent1);
		// Creating an error response.
		ResponseEntity<List<FlatRentEntity>> response = new ResponseEntity<>(rentList, HttpStatus.NOT_FOUND);
		if (!rentList.isEmpty()) {
			response = new ResponseEntity<>(rentList, HttpStatus.OK);
		}
	
			return response;
	}	
	
	@GetMapping(value = "/id/{userId}")
	public ResponseEntity<Object> getRent(@PathVariable("userId") int userId) {

		FlatRentEntity rent = flatRentService.getFlatRentById(userId);

		// Creating an error response.
		// ResponseEntity<Object> response =
		// ResponseEntity.status(HttpStatus.BAD_REQUEST)
		// .body("Message " + messageId + " Not found");

		// If message is not null it sets the message object in response else by default
		// error
		// will be there in response.
		if (rent == null) {
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new RentIdNotFoundException(userId + " Not Found");

		}

		return new ResponseEntity<>(rent, HttpStatus.OK);
	}
	
	@GetMapping(value = "/name/{ownerName}")
	public ResponseEntity<Object> getRentOwner(@PathVariable("ownerName") String ownerName) {

		FlatRentEntity rent1 = flatRentService.getFlatRentByOwnerName(ownerName);

		// Creating an error response.
		// ResponseEntity<Object> response =
		// ResponseEntity.status(HttpStatus.BAD_REQUEST)
		// .body("Message " + messageId + " Not found");

		// If message is not null it sets the message object in response else by default
		// error
		// will be there in response.
		if (rent1 == null) {
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new FlatIdNotFoundException(ownerName + " Not Found");

		}

		return new ResponseEntity<>(rent1, HttpStatus.OK);
	}
		
	
	@PostMapping
	public ResponseEntity<Object> addRent(@Valid @RequestBody FlatRentEntity rent) {
		// If message is inserted it returns inserted message object else null
		FlatRentEntity newRent = flatRentService.insertFlatRentEntity(rent);
		// response is set to error if message is null.
		if (newRent == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
		// response is set to inserted message id in response header section.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newRent.getUserId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	
	@DeleteMapping(value = "delete/{userId}")
	public ResponseEntity<Object> deleteRent(@PathVariable("userId") int id) {
		// If message is deleted it returns deleted message object else null
		FlatRentEntity rentPresent = flatRentService.deleteFlatRentEntity(id);
		// Creating an success response.
		// ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK)
		// .body("Message " + messageId + " deleted");
		// response is set to error if message is null.
		if (rentPresent == null) {
			// response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message " +
			// messageId + " Not found");
			throw new RentIdNotFoundException(id + " Not Found");
		}
		// return response;
		return ResponseEntity.status(HttpStatus.OK).body("Rent " + id + " deleted");
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateFlatRent(
			@RequestBody FlatRentEntity rent) {
		
		// If message is updated it returns updates message object else null
		FlatRentEntity updateRent = flatRentService.updateFlatRentEntity(rent);
		// response is set to error if message is null.
		if (updateRent == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message " + messageId + " Not found");
			throw new RentIdNotFoundException(rent + " Not Found");
		} else {
			// response is set to updated message id in response header section.
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(rent.getUserId()).toUri();
//			return ResponseEntity.created(location).build();
			return ResponseEntity.status(HttpStatus.OK).body("In " + rent + " updated");
		}
	}
	
	@GetMapping("/page")
    public ResponseEntity<List<FlatRentEntity>> getPageFlats(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "userId") String sortBy) 
    {
        List<FlatRentEntity> list = flatRentService.getPageRents(pageNo, pageSize, sortBy);
        if (list == null) {
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new FlatIdNotFoundException(list + " Not Found");

		}

		
        return new ResponseEntity<List<FlatRentEntity>> (list, new HttpHeaders(), HttpStatus.OK); 
    }

}
