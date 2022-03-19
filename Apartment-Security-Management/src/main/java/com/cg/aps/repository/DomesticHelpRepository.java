package com.cg.aps.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.cg.aps.entities.DomesticHelpEntity;

public interface DomesticHelpRepository extends JpaRepository<DomesticHelpEntity, String>{
	
//	@Query("select d from DomesticHelpEntity  d where d.name=?1")
	//List<DomesticHelpEntity> findbyName(String name);
	public DomesticHelpEntity name(String name);

}