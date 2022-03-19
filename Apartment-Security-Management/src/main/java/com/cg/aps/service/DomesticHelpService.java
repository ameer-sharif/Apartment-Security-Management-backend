package com.cg.aps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cg.aps.entities.DomesticHelpEntity;
import com.cg.aps.repository.DomesticHelpRepository;

@Service
public class DomesticHelpService implements IDomesticHelpService {
	
	@Autowired
	private DomesticHelpRepository domesticHelpRepository;
	
	@Override
	public List<DomesticHelpEntity> getHelp() {
		List<DomesticHelpEntity> domesticHelp = domesticHelpRepository.findAll();
		return domesticHelp;
	}
	
	@Override
	public DomesticHelpEntity deleteHelp(String id) {
		DomesticHelpEntity message = findByPk(id);
		if (message != null)
			domesticHelpRepository.deleteById(id);
		return message;
	}
	
/*	public DomesticHelpEntity add(DomesticHelpEntity domHelp) {
		DomesticHelpEntity helpexist = findByPk(domHelp.getFlatNo());
		if (helpexist == null) {
			domHelp = domHelpRep.save(domHelp);
		}
		return domHelp;
	}*/
	
	@Override
	public DomesticHelpEntity insertFno(DomesticHelpEntity domesticHelp) {
		DomesticHelpEntity messageexist = findByPk(domesticHelp.getFlatNo());
		if (messageexist == null) {
			domesticHelp = domesticHelpRepository.save(domesticHelp);
		}
		return domesticHelp;
	}
	
	@Override
	public DomesticHelpEntity findByPk(String id) {
		Optional<DomesticHelpEntity> message = domesticHelpRepository.findById(id);
		return (message.isPresent()) ? message.get() : null;
	}
	
	@Override
	public DomesticHelpEntity updateMessage(DomesticHelpEntity message) {
		DomesticHelpEntity messageexist = findByPk(message.getFlatNo());
		if (messageexist != null) {
			message = domesticHelpRepository.save(message);
		}
		return message;
	}
	
	@Override
	public DomesticHelpEntity findByName(String name1) {
		DomesticHelpEntity flatList = domesticHelpRepository.name(name1);
		return flatList;
	}
	
	@Override
	public List<DomesticHelpEntity> getDomByDom(DomesticHelpEntity flat1) {
		
//		FlatEntity employee = new FlatEntity();
//		 employee.setflat("Erika");
		 Example<DomesticHelpEntity> employeeExample = Example.of(flat1);
		List<DomesticHelpEntity> flatList = domesticHelpRepository.findAll(employeeExample); //FlatRepository.findById();
		return flatList;	
	}
	
	
	@Override
	public List<DomesticHelpEntity> getAllFlats(Integer pageNo, Integer pageSize, String sortBy)
	{
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    Page<DomesticHelpEntity> pagedResult = domesticHelpRepository.findAll(paging);
     
    if(pagedResult.hasContent()) {
        return pagedResult.getContent();
    	}
    else {
        return new ArrayList<DomesticHelpEntity>();
    	}
	}

	
/*	public DomesticHelpEntity findbyname(String nam) {
		List<DomesticHelpEntity> domhelp=domHelpRep.findbyName(nam);
		return (DomesticHelpEntity) domhelp;
	}*/
		
	
	
	
	

}