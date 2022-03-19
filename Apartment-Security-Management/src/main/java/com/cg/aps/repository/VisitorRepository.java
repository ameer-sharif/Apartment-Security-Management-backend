package com.cg.aps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.aps.entities.VisitorEntity;

@Repository
public interface VisitorRepository extends JpaRepository<VisitorEntity, String>{

	public VisitorEntity ownerName(String ownerName);
}