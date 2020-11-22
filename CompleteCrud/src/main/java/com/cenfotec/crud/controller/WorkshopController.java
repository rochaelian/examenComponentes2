package com.cenfotec.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cenfotec.crud.domain.Workshop;
import com.cenfotec.crud.service.AutorService;
import com.cenfotec.crud.service.CategoriaService;
import com.cenfotec.crud.service.TareaService;
import com.cenfotec.crud.service.WorkshopService;
import com.cenfotec.crud.domain.Autor;
import com.cenfotec.crud.domain.Categoria;
import com.cenfotec.crud.domain.Tarea;


@Controller
public class WorkshopController {

	@Autowired
	WorkshopService workshopService;
	
	@Autowired
	TareaService tareaService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	AutorService autorService;
	
	
	// Insertar workshops
	@RequestMapping(value = "/insertar",  method = RequestMethod.GET)
	public String insertarPage(Model model) {
		
		List<Categoria> listCategorias = categoriaService.getAll();
		List<Autor> listAutores = autorService.getAll();
		
		if (listCategorias.size() > 0) {
			model.addAttribute("categorias",listCategorias);
		}
		if(listAutores.size() > 0){
			model.addAttribute("autores",listAutores);
		}
		
		model.addAttribute(new Workshop());
		return "insertar";
	}	
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.POST)
	public String insertarAction(Workshop workshop, BindingResult result, Model model) {
		workshopService.save(workshop);
		return "index";
	}
	
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("workshops",workshopService.getAll());
		return "listar";
	}
	
	

	@RequestMapping("/actualizar/{id}")
	public String findWorkshopToEdit(Model model, @PathVariable long id) {
		Optional<Workshop> possibleData = workshopService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("workshopToActualizar",possibleData.get());
			return "actualizar";	
		}
		return "notfound";
	}
	
	@RequestMapping("/listarPorCategoria/{categoria}")
	public String findWorkshopCategporia(Model model, @PathVariable String categoria) {
		List<Workshop> possibleData = workshopService.find(categoria);
		if (possibleData.size() > 0) {
			model.addAttribute("workshopPorCategoria",possibleData);
			return "listaPorCategorias";	
		}
		return "notfound";
	}

	@RequestMapping(value="/actualizar/{id}",  method = RequestMethod.POST)
	public String saveEdition(Workshop workshop, Model model, @PathVariable long id) {
		workshopService.save(workshop);
		return "index";
	}

	@RequestMapping(value="/detalle/{id}")
	public String saveEdition(Model model, @PathVariable long id) {
		Optional<Workshop> possibleData = workshopService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("workshopData",possibleData.get());
			return "detalle";	
		}
		return "notfound";
	}

	@RequestMapping(value="/agregarTarea/{id}")
	public String recoverForAddTarea(Model model, @PathVariable long id) {
		Optional<Workshop> workshop = workshopService.get(id);
		Tarea newTarea = new Tarea();
		if (workshop.isPresent()) {
			newTarea.setWorkshop(workshop.get());
			
			model.addAttribute("workshop",workshop.get());
			model.addAttribute("tarea",newTarea);
			return "agregarTarea";	
		}
		return "notfound";
	}
	
	@RequestMapping(value="/agregarTarea/{id}", method = RequestMethod.POST)
	public String saveTarea(Tarea tarea, Model model, @PathVariable long id) {
		Optional<Workshop> workshop = workshopService.get(id);
		if (workshop.isPresent()) {
			tarea.setWorkshop(workshop.get());
			tareaService.save(tarea);
			return "index";
		}
		return "errorTarea";
	}
	
}
