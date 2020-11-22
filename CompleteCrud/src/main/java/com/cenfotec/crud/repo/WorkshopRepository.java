package com.cenfotec.crud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.crud.domain.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Long>{
	public List<Workshop> findByNameContaining(String word);
}
