package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entities.DomesticHelpEntity;

public interface IDomesticHelpService {

	
	public List<DomesticHelpEntity> getHelp();
	
	public DomesticHelpEntity deleteHelp(String id);
	
	public DomesticHelpEntity insertFno(DomesticHelpEntity domesticHelp);
	
	public DomesticHelpEntity findByPk(String id);
	
	public DomesticHelpEntity updateMessage(DomesticHelpEntity message);
	
	public DomesticHelpEntity findByName(String name1);
	
	public List<DomesticHelpEntity> getDomByDom(DomesticHelpEntity flat1);
	
	public List<DomesticHelpEntity> getAllFlats(Integer pageNo, Integer pageSize, String sortBy);
}