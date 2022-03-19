package com.cg.aps.service;

	
import java.util.List;


import com.cg.aps.entities.AdminEntity;


public interface IAdminService {
	public List<AdminEntity> getAdminEntity() ;
	public AdminEntity updatePassword(String emailId ,String resetpassword);


	public AdminEntity signIn(AdminEntity admin);
	public AdminEntity signOut(AdminEntity admin);	 
	 public AdminEntity getAdminEntityById(int id);
	
	AdminEntity insertAdminEntity(AdminEntity admin);

}