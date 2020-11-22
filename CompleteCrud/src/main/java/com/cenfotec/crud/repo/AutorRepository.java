package com.cenfotec.crud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.crud.domain.Autor;
import com.cenfotec.crud.domain.Workshop;

public interface AutorRepository extends JpaRepository<Autor,Long>{
	//public List<Autor> findByNameContaining(String word);
}
