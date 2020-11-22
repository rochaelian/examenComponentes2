package com.cenfotec.crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.crud.domain.Tarea;

public interface TareaRepository extends JpaRepository<Tarea,Long>{

}
