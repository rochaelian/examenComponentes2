package com.cenfotec.crud.service;

import java.util.List;
import java.util.Optional;

import com.cenfotec.crud.domain.Autor;

public interface AutorService {

	public void save(Autor autor);
	public Optional<Autor> get(Long id);
	public List<Autor> getAll();
	public List<Autor> find(String name);
	
}
