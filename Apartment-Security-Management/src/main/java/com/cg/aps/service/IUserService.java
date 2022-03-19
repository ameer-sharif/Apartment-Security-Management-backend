package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.UserEntity;

public interface IUserService {
	public List<UserEntity> getUserEntity() ;
	public List<UserEntity> getUserByUser(UserEntity user1);
	public UserEntity getUserUser(String firstName);
	public UserEntity insertUserEntity(UserEntity user) ;
	public UserEntity getUserEntity(int id) ;
	public UserEntity deleteUserEntity(int id) ;
	public UserEntity updateUserEntity(UserEntity user) ;
	public UserEntity updatePassword(String emailId ,String resetpassword);
	public List<UserEntity> getUserPage(Integer pageNo, Integer pageSize, String sortBy);
}