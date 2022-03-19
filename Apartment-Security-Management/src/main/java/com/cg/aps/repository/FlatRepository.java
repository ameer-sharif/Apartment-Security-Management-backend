package com.cg.aps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.aps.entities.FlatEntity;

@Repository
public interface FlatRepository extends JpaRepository<FlatEntity, Integer> {

	public FlatEntity ownerName(String ownerName);

}