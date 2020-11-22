package com.cenfotec.crud.service;

import java.util.List;
import java.util.Optional;

import com.cenfotec.crud.domain.Workshop;

public interface WorkshopService {

	public void save(Workshop workshop);
	public Optional<Workshop> get(Long id);
	public List<Workshop> getAll();
	public List<Workshop> find(String name);
	
}
