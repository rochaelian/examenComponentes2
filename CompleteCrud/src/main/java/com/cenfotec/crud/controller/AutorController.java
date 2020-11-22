package com.cenfotec.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cenfotec.crud.domain.Autor;
import com.cenfotec.crud.service.AutorService;


@Controller
public class AutorController {
	
	@Autowired
	AutorService autorService;
	
	
	@RequestMapping(value = "/insertarAutor",  method = RequestMethod.GET)
	public String insertarAutorPage(Model model) {
		model.addAttribute(new Autor());
		return "insertarAutor";
	}	
	
	@RequestMapping(value = "/insertarAutor",  method = RequestMethod.POST)
	public String insertarAction(Autor autor, BindingResult result, Model model) {
		autorService.save(autor);
		return "index";
	}

}
