package com.cenfotec.crud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cenfotec.crud.domain.Categoria;
import com.cenfotec.crud.domain.Workshop;
import com.cenfotec.crud.service.CategoriaService;

@Controller
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/insertarCategoria",  method = RequestMethod.GET)
	public String insertarCategoriaPage(Model model) {
		model.addAttribute(new Categoria());
		return "insertarCategoria";
	}	
	
	@RequestMapping(value = "/insertarCategoria",  method = RequestMethod.POST)
	public String insertarAction(Categoria categoria, BindingResult result, Model model) {
		categoriaService.save(categoria);
		return "index";
	}
	
	@RequestMapping("/listarCategorias")
	public String listar(Model model) {
		model.addAttribute("categorias",categoriaService.getAll());
		return "listarCategorias";
	}
	
	@RequestMapping("/actualizarCategoria/{id}")
	public String findCategoriaToEdit(Model model, @PathVariable long id) {
		Optional<Categoria> possibleData = categoriaService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("categoriaToActualizar",possibleData.get());
			return "actualizarCategoria";	
		}
		return "notfound";
	}

	@RequestMapping(value="/actualizarCategoria/{id}",  method = RequestMethod.POST)
	public String saveEdition(Categoria categoria, Model model, @PathVariable long id) {
		categoriaService.save(categoria);
		return "index";
	}

}
