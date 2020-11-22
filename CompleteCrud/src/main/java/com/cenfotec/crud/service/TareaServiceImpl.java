package com.cenfotec.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.crud.domain.Tarea;
import com.cenfotec.crud.repo.TareaRepository;

@Service
public class TareaServiceImpl implements TareaService{

	@Autowired
	TareaRepository tareaRepo;
	
	
	public void save(Tarea tarea) {
		tareaRepo.save(tarea);
	}
	
}
