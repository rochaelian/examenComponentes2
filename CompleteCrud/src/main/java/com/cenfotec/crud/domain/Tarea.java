package com.cenfotec.crud.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tarea {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	String name;
	String descripcion;
	String textoLeido;
	
	@ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
	private Workshop workshop;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTextoLeido() {
		return textoLeido;
	}

	public void setTextoLeido(String textoLeido) {
		this.textoLeido = textoLeido;
	}

	public Workshop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}
	
	
	
}
