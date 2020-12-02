package com.cenfotec.crud.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	@RequestMapping(value = "/insertar", method = RequestMethod.GET)
	public String insertarPage(Model model) {

		List<Categoria> listCategorias = categoriaService.getAll();
		List<Autor> listAutores = autorService.getAll();

		if (listCategorias.size() > 0) {
			model.addAttribute("categorias", listCategorias);
		}
		if (listAutores.size() > 0) {
			model.addAttribute("autores", listAutores);
		}

		model.addAttribute(new Workshop());
		return "insertar";
	}

	@RequestMapping(value = "/insertar", method = RequestMethod.POST)
	public String insertarAction(Workshop workshop, BindingResult result, Model model) {

		// Para categorias
		long idCat = workshop.getCategoria().getId();
		Optional<Categoria> possibleData = categoriaService.get(idCat);
		
		// Para autores
		long idAut = workshop.getAutor().getId();
		Optional<Autor> possibleDataAutor = autorService.get(idAut);
		
		Categoria actualizar = possibleData.get();
		Autor actualizarAutor = possibleDataAutor.get();
		
		
		categoriaService.save(actualizar);	
		autorService.save(actualizarAutor);
		workshopService.save(workshop);
		
		return "index";

	}

	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("workshops", workshopService.getAll());
		return "listar";
	}
    
	// Workshops por categoria
	@RequestMapping("/listarWorkshopCategoria")
	public String listarWorkshopCategoria(Model model) {
		model.addAttribute("categorias", categoriaService.getAll());
		return "listarWorkshopCategoria";
	}
	
	@RequestMapping(value = "/workshopDeCategoria/{id}")
	public String saveEditionCat(Model model, @PathVariable long id) {
		Optional<Categoria> possibleData = categoriaService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("categoriaData", possibleData.get());
			return "workshopDeCategoria";
		}
		return "notfound";
	}
	
	// Workshops por autor
	@RequestMapping("/listarWorkshopAutor")
	public String listarWorkshopAutor(Model model) {
		model.addAttribute("autores", autorService.getAll());
		return "listarWorkshopAutor";
	}
	
	@RequestMapping(value = "/workshopDeAutor/{id}")
	public String saveEditionAut(Model model, @PathVariable long id) {
		Optional<Autor> possibleData = autorService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("autorData", possibleData.get());
			return "workshopDeAutor";
		}
		return "notfound";
	}

	//Actualizar workshops
	@RequestMapping("/actualizar/{id}")
	public String findWorkshopToEdit(Model model, @PathVariable long id) {
		Optional<Workshop> possibleData = workshopService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("workshopToActualizar", possibleData.get());
			return "actualizar";
		}
		return "notfound";
	}

	@RequestMapping(value = "/actualizar/{id}", method = RequestMethod.POST)
	public String saveEdition(Workshop workshop, Model model, @PathVariable long id) {
		workshopService.save(workshop);
		return "index";
	}

	@RequestMapping(value = "/detalle/{id}")
	public String saveEdition(Model model, @PathVariable long id) {
		Optional<Workshop> possibleData = workshopService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("workshopData", possibleData.get());
			return "detalle";
		}
		return "notfound";
	}

	@RequestMapping(value = "/agregarTarea/{id}")
	public String recoverForAddTarea(Model model, @PathVariable long id) {
		Optional<Workshop> workshop = workshopService.get(id);
		Tarea newTarea = new Tarea();
		if (workshop.isPresent()) {
			newTarea.setWorkshop(workshop.get());

			model.addAttribute("workshop", workshop.get());
			model.addAttribute("tarea", newTarea);
			return "agregarTarea";
		}
		return "notfound";
	}

	@RequestMapping(value = "/agregarTarea/{id}", method = RequestMethod.POST)
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
