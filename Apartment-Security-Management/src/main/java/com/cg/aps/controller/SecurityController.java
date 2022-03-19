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

import com.cg.aps.entities.DomesticHelpEntity;
import com.cg.aps.entities.SecurityEntity;
import com.cg.aps.service.SecurityService;
import com.cg.aps.exception.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/apart")
public class SecurityController {
	
	@Autowired
	private SecurityService securityService;
	
	Logger log=LoggerFactory.getLogger(SecurityController.class);
	
	@GetMapping
	public ResponseEntity<List<SecurityEntity>> getSecurity() {
		log.info("Get security method called");
		List<SecurityEntity> secureList = securityService.getSecure();
		// Creating an error response.
		ResponseEntity<List<SecurityEntity>> response = new ResponseEntity<>(secureList, HttpStatus.NOT_FOUND);
		log.error("Security does not have any data");

		// If messageList is not empty it sets the list in response else by default
		// error will be
		// there in response.
		if (!secureList.isEmpty()) {
			log.info(" Get Security executed");
			response = new ResponseEntity<>(secureList, HttpStatus.OK);
		}

		return response;
	}
	
	@DeleteMapping(value = "/{Id}")
	public ResponseEntity<Object> deleteSecurity(@PathVariable("Id") String messageId) {
		log.info("Delete message called");
		// If message is deleted it returns deleted message object else null
		SecurityEntity messagePresent = securityService.deleteMsg(messageId);
		// Creating an success response.
		 ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK)
		 .body("Message " + messageId + " deleted");
		 log.info("message deleted");
		// response is set to error if message is null.
		if (messagePresent == null) {
			log.error("Message id not found");
			//response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message " +
			//messageId + " Not found");
			throw new MessageIdNotFoundException(messageId + " Not Found");
		}
		 return response;
		//return ResponseEntity.status(HttpStatus.OK).body("Message " + messageId + " deleted");
	}
	
	@PostMapping
	public ResponseEntity<Object> addMessage(@RequestBody SecurityEntity message) {
		log.info("Add message called");
		// If message is inserted it returns inserted message object else null
		SecurityEntity newMessage = securityService.addMsg(message);
		// response is set to error if message is null.
		if (newMessage == null) {
			log.error("Enter all the details");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
		}// response is set to inserted message id in response header section.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}")
				.buildAndExpand(newMessage.getMsgId()).toUri();
		log.info("Message added");
		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping(value = "/{MsgId}")
	public ResponseEntity<Object> getMessage(@RequestBody @PathVariable("MsgId") String MsgId) {
		log.info("Get message by id is called");

		SecurityEntity securityHelp = securityService.findByPk(MsgId);
		
		// Creating an error response.
		 ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message " + MsgId + " Not found");
		 

		// If message is not null it sets the message object in response else by default
		// error
		// will be there in response.
		if (securityHelp == null) {
			log.error("Message id not found");
			 //response = new ResponseEntity<>(secHelp, HttpStatus.OK);
			throw new MessageIdNotFoundException(MsgId + " Not Found");

		}
		log.info("message fetched");
		return new ResponseEntity<>(securityHelp, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Object> updateProduct(
			@RequestBody SecurityEntity message) {
		log.info("Update method called");
	//	message.setMsgId(msgId);
		// If message is updated it returns updates message object else null
		SecurityEntity updateMessage = securityService.updateMessage(message);
		// response is set to error if message is null.
		if (updateMessage == null) {
			log.error("Message ID not found");
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message " + msgId + " Not found");
			throw new MessageIdNotFoundException(message + " Not Found");
		} else {
			log.info("Message updated");
			// response is set to updated message id in response header section.
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{msgId}")
					.buildAndExpand(message.getMsgId()).toUri();
			return ResponseEntity.created(location).build();
		}
	}
	
	@GetMapping("/sec")
	 public ResponseEntity<List<SecurityEntity>> findSecBySec(SecurityEntity flat1 ) {
		log.info("Get Security by Security ");
		System.out.println("**"+flat1+"**");
		List<SecurityEntity> flatList = securityService.getSecBySec(flat1);
		// Creating an error response.
		ResponseEntity<List<SecurityEntity>> response = new ResponseEntity<>(flatList, HttpStatus.NOT_FOUND);
		log.error("Flat List not found");
		if (!flatList.isEmpty()) {
			log.info("flatList fetched");
			response = new ResponseEntity<>(flatList, HttpStatus.OK);
		}

		return response;
	}
	
	@GetMapping("/page")
    public ResponseEntity<List<SecurityEntity>> getAllSecure(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "userId") String sortBy) 
    {
		log.info("Pagination method called");
        List<SecurityEntity> list = securityService.getAllFlats(pageNo, pageSize, sortBy);
        if (list == null) {
        	log.error("Give valid page number,page size,valid field name");
			// response = new ResponseEntity<>(message, HttpStatus.OK);
			throw new MessageIdNotFoundException(list + " Not Found");

		}

        return new ResponseEntity<List<SecurityEntity>> (list, new HttpHeaders(), HttpStatus.OK); 
    }

}
