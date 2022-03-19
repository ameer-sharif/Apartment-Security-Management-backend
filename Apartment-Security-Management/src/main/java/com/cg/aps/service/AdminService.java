package com.cg.aps.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.aps.entities.AdminEntity;


import com.cg.aps.exception.AdminIdNotFoundException;
import com.cg.aps.repository.AdminRepository;

@Service
@Transactional
public class AdminService implements IAdminService {

	@Autowired
	private AdminRepository adminRepository;

	Logger logger = LoggerFactory.getLogger(AdminService.class);


	
	@Override
	public List<AdminEntity> getAdminEntity() {
		logger.info("searched values found successfully....");
		List<AdminEntity> adminList = adminRepository.findAll();
		return adminList;
	}
	
	@Override
	public AdminEntity updatePassword(String emailId ,String resetpassword) {
		logger.info("password updated...");
		AdminEntity admin = adminRepository.emailId(emailId);
		if ( admin != null){
			admin.setPassword(resetpassword);
			admin = adminRepository.save(admin);
		}
		return admin;
	}
	
	@Override
	public AdminEntity insertAdminEntity(AdminEntity admin) {
		logger.info("admin added successfuly...");
		AdminEntity admin1 = getAdminEntityById(admin.getAdminId());
		if (admin1== null) {
			admin = adminRepository.save(admin);
		}
	return admin;
	}
	
	@Override
	public AdminEntity getAdminEntityById(int id) {
		logger.info("user by id found successfuly...");
		Optional<AdminEntity> admin = adminRepository.findById(id);
		return (admin.isPresent()) ? admin.get() : null;
	}



	@Override
	public AdminEntity signIn(AdminEntity admin) {
		logger.info("Inside signIn method");
		String password = admin.getPassword();
		String emailId = admin.getEmailId();
		AdminEntity adminData = adminRepository.findByPasswordAndEmailId( password, emailId);
		if (adminData == null) {
			logger.error("AdminNotFoundException in adminSignIn method");
			throw new AdminIdNotFoundException("No admin present"); 
		} else {

			return adminData;
		}

	}
	
	@Override
	public AdminEntity signOut(AdminEntity admin) {

		return admin;
	}
	
	public AdminEntity addAdmin(AdminEntity admin) {
		
		return adminRepository.save(admin);
	}
	
public Object fetchAdminByEmailId(String emailId) {


		return adminRepository.getAdminByEmailId(emailId);
	}
}

//	@Override
//	public AdminEntity getAdminEntityById(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	
//	@Override
//	public AdminEntity addNewAdmin(AdminEntity admin) {
//
//		return null;
//	}
//


	
