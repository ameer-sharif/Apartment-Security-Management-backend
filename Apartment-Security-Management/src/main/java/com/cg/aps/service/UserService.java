package com.cg.aps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.cg.aps.entities.UserEntity;
import com.cg.aps.repository.UserRepository;



@Service
public class UserService implements IUserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;

	
	//get all
	@Override
	public List<UserEntity> getUserEntity() {
		logger.info("searched values found successfully....");
		List<UserEntity> userList = userRepository.findAll();
		return userList;
	}
	
	
	//user by user
	@Override
	public List<UserEntity> getUserByUser(UserEntity user1) {
		logger.info("user founnd successfully....");
		Example<UserEntity> employeeExample = Example.of(user1);
		List<UserEntity> userList = userRepository.findAll(employeeExample);
		return userList;	
	}
	
	
	//user by name
	@Override
	public UserEntity getUserUser(String firstName) {
		logger.info("user is found by firstname successfully..... ");
		UserEntity user1 = userRepository.firstName(firstName);
		return user1;
	}
	
	
	//post
	@Override
	public UserEntity insertUserEntity(UserEntity user) {
		logger.info("user added successfuly...");
		UserEntity user1 = getUserEntity(user.getUserId());
		if (user1== null) {
			user = userRepository.save(user);
		}
	return user;
	}
	
	
	//user by id
	@Override
	public UserEntity getUserEntity(int id) {
		logger.info("user by id found successfuly...");
		Optional<UserEntity> user = userRepository.findById(id);
		return (user.isPresent()) ? user.get() : null;
	}

	
	//delete
	@Override
	public UserEntity deleteUserEntity(int id) {
		logger.info("user deleted successfully.....");
		UserEntity user = getUserEntity(id);
		if ( user != null)
			 userRepository.deleteById(id);
		return user;
	}

	
	
	//put
	@Override
	public UserEntity updateUserEntity(UserEntity user) {
		logger.info("user updated successfully.....");
		UserEntity userexsist = getUserEntity(user.getUserId());
		if ( userexsist != null){
			user = userRepository.save(user);
		}
		return user;
	}
	
	
	//password reset (patch)
	@Override
	public UserEntity updatePassword(String emailId ,String resetpassword) {
		logger.info("password updated...");
		UserEntity user = userRepository.emailId(emailId);
		if ( user != null){
			user.setPassword(resetpassword);
			user = userRepository.save(user);
		}
		return user;
	}
	
	
	//pagennation
	@Override
	public List<UserEntity> getUserPage(Integer pageNo, Integer pageSize, String sortBy)
	{
		logger.info("paginnition donne successfully.....");
	    PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	    Page<UserEntity> pagedResult = userRepository.findAll(paging);  
	    if(pagedResult.hasContent()) {
	        return pagedResult.getContent();
	    } else {
	        return new ArrayList<UserEntity>();
	    }
	}
}