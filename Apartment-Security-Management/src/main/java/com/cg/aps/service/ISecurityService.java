package com.cg.aps.service;


import java.util.List;

import com.cg.aps.entities.SecurityEntity;

public interface ISecurityService {
	
	public List<SecurityEntity> getSecure();
	
	public SecurityEntity addMsg(SecurityEntity secHelp);
	
	public SecurityEntity deleteMsg(String id);
	
	public SecurityEntity findByPk(String id);
	
	public SecurityEntity updateMessage(SecurityEntity message);
	
	public List<SecurityEntity> getSecBySec(SecurityEntity flat1);
	
	public List<SecurityEntity> getAllFlats(Integer pageNo, Integer pageSize, String sortBy);

}
