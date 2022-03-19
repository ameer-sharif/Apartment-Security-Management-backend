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
import com.cg.aps.entities.SecurityEntity;
import com.cg.aps.repository.SecurityRepository;

@Service
public class SecurityService implements ISecurityService{
	
	@Autowired
	private SecurityRepository securityRepository;
	
	@Override
	public List<SecurityEntity> getSecure() {
		List<SecurityEntity> securityHelp = securityRepository.findAll();
		return securityHelp;
	}
	
	@Override
	public SecurityEntity addMsg(SecurityEntity secHelp) {
		SecurityEntity messageExist = findByPk(secHelp.getMsgId());
		if (messageExist == null) {
			secHelp = securityRepository.save(secHelp);
		}
		return secHelp;
	}
	
	@Override
	public SecurityEntity deleteMsg(String id) {
		SecurityEntity message = findByPk(id);
		if (message != null)
			securityRepository.deleteById(id);
		return message;
	}
	
	@Override
	public SecurityEntity findByPk(String id) {
		Optional<SecurityEntity> message = securityRepository.findById(id);
		return (message.isPresent()) ? message.get() : null;
	}
	
	
	@Override
	public SecurityEntity updateMessage(SecurityEntity message) {
		SecurityEntity messageexist = findByPk(message.getMsgId());
		if (messageexist != null) {
			message = securityRepository.save(message);
		}
		return message;
	}
	
	@Override
	public List<SecurityEntity> getSecBySec(SecurityEntity flat1) {
		
//		FlatEntity employee = new FlatEntity();
//		 employee.setflat("Erika");
		 Example<SecurityEntity> employeeExample = Example.of(flat1);
		List<SecurityEntity> flatList = securityRepository.findAll(employeeExample); //FlatRepository.findById();
		return flatList;	
	}
	
	@Override
	public List<SecurityEntity> getAllFlats(Integer pageNo, Integer pageSize, String sortBy)
	{
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    Page<SecurityEntity> pagedResult = securityRepository.findAll(paging);
     
    if(pagedResult.hasContent()) {
        return pagedResult.getContent();
    } else {
        return new ArrayList<SecurityEntity>();
    }
}
	

}
