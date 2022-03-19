package com.cg.aps.controller;
	import java.net.URI;
import java.util.List;

	import javax.servlet.http.HttpServletRequest;
//	import javax.servlet.http.HttpSession;
	import javax.validation.Valid;

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.aps.entities.AdminEntity;

import com.cg.aps.exception.AdminIdNotFoundException;

import com.cg.aps.service.AdminService;
import com.cg.aps.service.IAdminService;



	@RestController
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/admin")
	public class AdminController {

		@Autowired
		private AdminService adminService;
		Logger logger = LoggerFactory.getLogger(AdminController.class);
		
	
//		@ExceptionHandler
		
		@GetMapping
		public ResponseEntity<List<AdminEntity>> getAdminEntity(HttpServletRequest request ) {
			logger.info("searching....");
//			validateTokenService.validateToken(request);
			List<AdminEntity> adminList = adminService.getAdminEntity();
			ResponseEntity<List<AdminEntity>> response = new ResponseEntity<>(adminList, HttpStatus.NOT_FOUND);
			if (!adminList.isEmpty()) { 
				logger.info("Values are found....");
				response = new ResponseEntity<>(adminList, HttpStatus.OK);
			} else {
				logger.error("No flats are found");
				throw new AdminIdNotFoundException(adminList + " Not Found");


			}
			return response;
		}
		
		@GetMapping(value = "/id/{userId}")
		public ResponseEntity<Object> getAdminById(@PathVariable("adminId") int adminId) {
			logger.info("user searched by Id....");
			AdminEntity admin1 = adminService.getAdminEntityById(adminId);
			if (admin1 == null) {
				logger.error("id is not found.....");
				throw new AdminIdNotFoundException(adminId + " Not Found");

			}
			logger.info("Id found....");
			return new ResponseEntity<>(admin1, HttpStatus.OK);
		}
		
		

		@PostMapping("/registerForm")
		public ResponseEntity<String> addUser(@RequestBody AdminEntity admin) {
			if(adminService.fetchAdminByEmailId(admin.getEmailId())!=null){
				logger.info("Email already exists");
				 return new ResponseEntity<> ("Email Id already exists..", HttpStatus.BAD_REQUEST);
			}
			else {
				AdminEntity newAdmin = adminService.addAdmin(admin);
			logger.info("adding user");
			if (newAdmin == null)
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newAdmin.getAdminId()).toUri();
			return ResponseEntity.created(location).build();
			}
		}

		@PatchMapping("partial/{emailId}/{resetpassword}")
		public ResponseEntity<String> updatePassword(@PathVariable("resetpassword") String resetpassword , @PathVariable("emailId") String emailId){	
			logger.info("searching mail id....");
			AdminEntity updateAdmin = adminService.updatePassword(emailId, resetpassword);
			if (updateAdmin == null){
			logger.error("not found....");
			throw new AdminIdNotFoundException(emailId + " Not Found");
			} else {
			logger.info("found...");
			return new ResponseEntity<String>("password successfully updated", HttpStatus.OK) ;
			}	
		}

		@PostMapping("/signIn")
		public ResponseEntity<String> adminSignin(@Valid @RequestBody AdminEntity admin) {
			logger.info("Inside adminSignin method");
			AdminEntity adminData = adminService.signIn(admin);

			return new ResponseEntity<String>("Sign in succesful, welcome admin: " + adminData.getAdminId(), HttpStatus.OK);
		}
		
		
//		@PostMapping("/signOut")
//		public ResponseEntity<String> adminSignOut(@Valid @RequestBody AdminEntity admin, HttpServletRequest request) {
//			HttpSession session = request.getSession();
//			session.removeAttribute("adminExists");
//			session.invalidate();
//			logger.info("Inside adminSignOut method");
//			AdminEntity adminData = service.signOut(admin);
//
//			return new ResponseEntity<String>("Sign Out succesfull, Bye admin: " + adminData.getAdminId(), HttpStatus.OK);
//		}
		
	}