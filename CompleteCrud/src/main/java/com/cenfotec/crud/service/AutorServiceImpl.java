package com.cenfotec.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.crud.domain.Autor;
import com.cenfotec.crud.repo.AutorRepository;

@Service
public class AutorServiceImpl implements AutorService{

	@Autowired
	AutorRepository autorRepo;
	
	
	public void save(Autor autor) {
		autorRepo.save(autor);
	}

	@Override
	public Optional<Autor> get(Long id) {
		return autorRepo.findById(id);
	}


	@Override
	public List<Autor> getAll() {
		return autorRepo.findAll();
	}

	@Override
	public List<Autor> find(String name) {
		return null;
	}	
}
