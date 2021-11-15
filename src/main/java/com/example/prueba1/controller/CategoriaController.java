package com.example.prueba1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.prueba1.editors.CategoriaEditor;
import com.example.prueba1.models.domain.Categoria;
import com.example.prueba1.services.CategoriaService;
import com.example.prueba1.util.PageRender;

@Controller
@SessionAttributes("categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private CategoriaEditor categoriaEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Categoria.class, "categoria", categoriaEditor);
	}

	@RequestMapping(value = "indexCategoria", method = RequestMethod.GET)
	public String indexCategoria(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pager = PageRequest.of(page, 5);

		Page<Categoria> libros = categoriaService.findAll(pager);
		PageRender<Categoria> pageRender = new PageRender<>("/index", libros);

		model.addAttribute("titulo", "Listado de libros");
		model.addAttribute("libros", libros);
		model.addAttribute("page", pageRender);
		
		return "contenido/contCategoria";
	}

	@GetMapping("/crearCategoria")
	public String crearCategoria(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("categoria", categoria);
		return "crear/crearCategoria";
	}

	@PostMapping("/formCategoria")
	public String formCategoria(@Valid Categoria categoria, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			return "crear/crearCategoria";
		}

		categoriaService.save(categoria);

		return "redirect:indexCategoria";
	}

	@GetMapping("/modificarCat/{id}")
	public String modificarCategoria(@SessionAttribute(name = "categoria", required = false) Categoria categoria,
			@PathVariable Long id, Model model) {
		categoria = categoriaService.findOne(id);
		model.addAttribute("categoria", categoria);
		return "modificar/modificarCategoria";
	}

	/* @PostMapping("/modCategoria")
	public String mobCategoria(@Valid Categoria categoria, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			return "modificar/modificarCategoria";
		}
		categoriaService.modificarCategoria(categoria);

		return "listas/listaCategoria";
	} */

	@GetMapping("/eliminarCategoria/{id}")
	public String eliminarCategoria(@SessionAttribute(name = "categoria", required = false) Categoria categoria,
			@PathVariable Long id, Model model) {
		categoriaService.delete(id);
		setValues(0, model);
		return "contenido/contCategoria";
	}

	public void setValues(int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Categoria> categorias = categoriaService.findAll(pageRequest);
        PageRender<Categoria> pageRender = new PageRender<>("/indexCategoria", categorias);
        model.addAttribute("categorias", categorias);
        model.addAttribute("page", pageRender);
    }

	@GetMapping("/listaCategoria")
	public String listaCategoria(Model model) {
		return "listas/listaCategoria";
	}

	@ModelAttribute("categorias")
	public List<Categoria> categorias() {
		return categoriaService.findAll();
	}

}
