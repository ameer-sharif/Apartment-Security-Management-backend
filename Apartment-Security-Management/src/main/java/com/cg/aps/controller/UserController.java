package com.cg.aps.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.cg.aps.entities.UserEntity;
import com.cg.aps.exception.UserIdNotFoundException;
import com.cg.aps.service.UserService;
//import com.cg.aps.service.ValidateTokenService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")



public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	ArrayList<UserEntity> userList = new ArrayList<>();
	@Autowired
	private UserService userService;
	
	
	@GetMapping
	public ResponseEntity<List<UserEntity>> getUserEntity(HttpServletRequest request ) {
		logger.info("searching....");
		
		List<UserEntity> userList = userService.getUserEntity();
		ResponseEntity<List<UserEntity>> response = new ResponseEntity<>(userList, HttpStatus.INTERNAL_SERVER_ERROR);
		if (!userList.isEmpty()) { 
			logger.info("Values are found....");
			response = new ResponseEntity<>(userList, HttpStatus.OK);
		}
		return response;
	}
	

	@GetMapping("/user")
	public ResponseEntity<List<UserEntity>> getUserByUser(UserEntity user1, HttpServletRequest request ) {
		logger.info("searchig user....");
//		validateTokenService.validateToken(request);
		List<UserEntity> userList = userService.getUserByUser(user1);
		ResponseEntity<List<UserEntity>> response = new ResponseEntity<>(userList, HttpStatus.NOT_FOUND);
		if (!userList.isEmpty()) {
			logger.info("searched..... ");
			response = new ResponseEntity<>(userList, HttpStatus.OK);
			}
		return response;
		}
	

	@GetMapping(value = "/id/{userId}")
	public ResponseEntity<Object> getuser(@PathVariable("userId") int userId) {
		logger.info("user searched by Id....");
		UserEntity flat = userService.getUserEntity(userId);
		if (flat == null) {
			logger.error("id is not found.....");
			throw new UserIdNotFoundException(userId + " Not Found");

		}
		logger.info("Id found....");
		return new ResponseEntity<>(flat, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{firstName}")
	public ResponseEntity<Object> getUserUser(@PathVariable("firstName") String firstName) {
		logger.info("user searched by firstName");
		UserEntity user1 = userService.getUserUser(firstName);
		if (user1 == null) {
			logger.info("Inside addAdmin method");
			throw new UserIdNotFoundException(firstName + " Not Found");
		}
		return new ResponseEntity<>(user1, HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<Object> addUser(@RequestBody UserEntity user) {
		logger.info("Inserting the user details....");
		UserEntity newUser = userService.insertUserEntity(user);
		if (newUser == null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getUserId()).toUri();
		logger.info("user is added...");
		return ResponseEntity.created(location).build();
	}
	
	

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable("userId") int userId){
		 logger.info("user is deleting.....");
		UserEntity user = userService.deleteUserEntity(userId);
		if (user == null) {
			logger.error("user is not found to delete....");
			throw new UserIdNotFoundException(userId + " Not Found");
		}
		 logger.info("User is deleted....");
		return ResponseEntity.status(HttpStatus.OK).body("User " + userId + " deleted");
	}
	

	@PutMapping
	public ResponseEntity<Object> updateUser(@RequestBody UserEntity user ){
		logger.info("user is updating....");
//		user.setUserId(userId);
		UserEntity updateUser = userService.updateUserEntity(user);
		if (updateUser == null) {
			logger.error("user is not updated.....");
		throw new UserIdNotFoundException(user + " Not Found");
		} else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(user.getUserId()).toUri();
			logger.info("user id updated......");
			return ResponseEntity.created(location).build();
		}
	}
	

	@PostMapping("/registerForm")
	public ResponseEntity<Object> registerUserForm(@ModelAttribute("users") UserEntity user) {
		 logger.info("Inside addAdmin method");
		UserEntity newUser = userService.insertUserEntity(user);
		if (newUser == null)
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
		logger.info("Inside addAdmin method");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{registerForm}").buildAndExpand(newUser.getUserId()).toUri();
		return ResponseEntity.created(location).build();
		}


	@PatchMapping("partial/{emailId}/{resetpassword}")
	public ResponseEntity<String> updatePassword(@PathVariable("resetpassword") String resetpassword , @PathVariable("emailId") String emailId){	
		logger.info("searching mail id....");
		UserEntity updateUser = userService.updatePassword(emailId, resetpassword);
		if (updateUser == null){
		logger.error("not found....");
		throw new UserIdNotFoundException(emailId + " Not Found");
		} else {
		logger.info("found...");
		return new ResponseEntity<String>("password successfully updated", HttpStatus.OK) ;
		}	
	}


	@GetMapping("/page")
	public ResponseEntity<List<UserEntity>> getUserPage(@RequestParam(defaultValue = "0") Integer pageNo,  @RequestParam(defaultValue = "10") Integer pageSize,  @RequestParam(defaultValue = "userId") String sortBy) 
	{           
		logger.info("paginnation starting.....");
	    List<UserEntity> list = userService.getUserPage(pageNo, pageSize, sortBy);
	    if (list == null) {
	    	logger.error("  page not found.."); 
			throw new UserIdNotFoundException(list + " Not Found");
	
		}
	    logger.info("showing list of values in page.....");
	    return new ResponseEntity<List<UserEntity>> (list, new HttpHeaders(), HttpStatus.OK); 
		}
	
}