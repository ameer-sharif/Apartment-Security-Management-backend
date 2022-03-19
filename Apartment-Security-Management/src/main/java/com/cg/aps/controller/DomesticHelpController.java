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

import com.cg.aps.entities.*;
import com.cg.aps.exception.FlatIdNotFoundException;
import com.cg.aps.service.DomesticHelpService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/aps")
public class DomesticHelpController {
	
	@Autowired
	private DomesticHelpService domesticHelpService;
	
	Logger log=LoggerFactory.getLogger(DomesticHelpController.class);
	
	@GetMapping
	public ResponseEntity<List<DomesticHelpEntity>> getFlatDetails() {
		log.info("Get Flat details started");

		List<DomesticHelpEntity> flatList = domesticHelpService.getHelp();
		// Creating an error response.
		ResponseEntity<List<DomesticHelpEntity>> response = new ResponseEntity<>(flatList, HttpStatus.NOT_FOUND);
		//log.error("MessageList does not exist");
		// If messageList is not empty it sets the list in response else by default
		// error will be
		// there in response.
		if (!flatList.isEmpty()) {
			log.info("Fetched flat list details");
			response = new ResponseEntity<>(flatList, HttpStatus.OK);
		}

		return response;
	}
	
	@DeleteMapping(value = "/{Id}")
	public ResponseEntity<Object> deleteFlat(@PathVariable("Id") String flatId) {
		log.info("Delete method called");
		// If message is deleted it returns deleted message object else null
		DomesticHelpEntity flatPresent = domesticHelpService.deleteHelp(flatId);
		// Creating an success response.
		 ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK)
		 .body("Message " + flatId + " deleted");
		 log.info("domestic help deleted");
		// response is set to error if message is null.
		if (flatPresent == null) {
			log.error("Flat Id not found");
			//response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message " +
			//messageId + " Not found");
			throw new FlatIdNotFoundException(flatId + " Not Found");
		}
		 return response;
		//return ResponseEntity.status(HttpStatus.OK).body("Message " + messageId + " deleted");
	}

	@PostMapping
	public ResponseEntity<Object> addFlat(@RequestBody DomesticHelpEntity flat) {
		log.info("Post method called");
		// If message is inserted it returns inserted message object else null
		DomesticHelpEntity newFlat = domesticHelpService.insertFno(flat);
		// response is set to error if message is null.
		if (newFlat == null) {
			log.error("details needs to be filled");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
		}
		// response is set to inserted message id in response header section.
		log.info("Post method executed");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newFlat.getFlatNo()).toUri();
		return ResponseEntity.created(location).build();
	}
	

	@GetMapping(value = "/{FlatNo}")
	public ResponseEntity<Object> getMessage(@PathVariable("FlatNo") String FlatNo) {
		log.info("get mapping called");
		DomesticHelpEntity domesticHelp = domesticHelpService.findByPk(FlatNo);

		// Creating an error response.
		 ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Flat Number " + FlatNo + " Not found");
		

		// If message is not null it sets the message object in response else by default
		// error
		// will be there in response.
		if (domesticHelp == null) {
			 log.error("Flat no not found");
			 //response = new ResponseEntity<>(domHelp, HttpStatus.OK);
			throw new FlatIdNotFoundException(FlatNo + " Not Found");

		}
		log.info("get by id executed");
		return new ResponseEntity<>(domesticHelp, HttpStatus.OK);
	}
	

	@PutMapping
	public ResponseEntity<Object> updateProduct(@RequestBody DomesticHelpEntity flat) {
		log.info("upadte method called ");
		// If message is updated it returns updates message object else null
		DomesticHelpEntity updateFlat = domesticHelpService.updateMessage(flat);
	
		// response is set to error if message is null.
		if (updateFlat == null) {
			log.error("flat no not found");
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message " + flatNo + " Not found");
			throw new FlatIdNotFoundException(flat + " Not Found");
		} else {
			log.info("update method executed");
			// response is set to updated message id in response header section.
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{flatNo}")
					.buildAndExpand(flat.getFlatNo()).toUri();
			return ResponseEntity.created(location).build();
		}
	}
	
	@GetMapping(value="/nam/{name}")
	public ResponseEntity<Object> findByName(@PathVariable("name") String name) throws Exception {
		log.info("get by name called");

		DomesticHelpEntity domesticHelp = domesticHelpService.findByName(name);

		// Creating an error response.
		 //ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.BAD_REQUEST);
				 //.body("Message " + name + " Not found");

		// If message is not null it sets the message object in response else by default
		// error
		// will be there in response.
		if (domesticHelp == null) {
			log.error("name not found");
			 //response = new ResponseEntity<>(domHelp, HttpStatus.OK);
			throw new Exception(name + " Not Found");

		}
		log.info("get by name executed");
		return new ResponseEntity<>(domesticHelp, HttpStatus.OK);
	}
	
	@GetMapping("/dom")
	 public ResponseEntity<List<DomesticHelpEntity>> findDomByDom(DomesticHelpEntity flat1 ) {
		log.info("Get dom by dom called");
		System.out.println("**"+flat1+"**");
		List<DomesticHelpEntity> flatList = domesticHelpService.getDomByDom(flat1);
		// Creating an error response.
		ResponseEntity<List<DomesticHelpEntity>> response = new ResponseEntity<>(flatList, HttpStatus.NOT_FOUND);
		
		if (!flatList.isEmpty()) {
			log.info("Dom By Dom executed");
			response = new ResponseEntity<>(flatList, HttpStatus.OK);
		}
		log.error("did not fetch by DOM object");
		return response;
	}
	
	@GetMapping("/page")
    public ResponseEntity<List<DomesticHelpEntity>> getAllFlats(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "userId") String sortBy) 
     {
		log.info("Pagination called");
        List<DomesticHelpEntity> list = domesticHelpService.getAllFlats(pageNo, pageSize, sortBy);
        if (list == null) {
        	log.error("list not found");
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new FlatIdNotFoundException(list + " Not Found");

		}

		
        return new ResponseEntity<List<DomesticHelpEntity>> (list, new HttpHeaders(), HttpStatus.OK); 
    }
	
}