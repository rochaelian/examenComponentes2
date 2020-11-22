package com.cenfotec.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.crud.domain.Categoria;
import com.cenfotec.crud.repo.CategoriaRepository;
@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	CategoriaRepository categoriaRepo;
	
	
	public void save(Categoria categoria) {
		categoriaRepo.save(categoria);
	}

	@Override
	public Optional<Categoria> get(Long id) {
		return categoriaRepo.findById(id);
	}


	@Override
	public List<Categoria> getAll() {
		return categoriaRepo.findAll();
	}

	@Override
	public List<Categoria> find(String name) {
		return null;
	}
	
}
