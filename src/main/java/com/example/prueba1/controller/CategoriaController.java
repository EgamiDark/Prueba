package com.example.prueba1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.prueba1.editors.CategoriaEditor;
import com.example.prueba1.models.domain.Categoria;
import com.example.prueba1.services.CategoriaService;

@Controller
@SessionAttributes("categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private CategoriaEditor categoriaEditor;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Categoria.class,"categoria",categoriaEditor);
	}
	
	@GetMapping("/crearCategoria")
	public String crearCategoria(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("categoria",categoria);
		return "crear/crearCategoria";
	}
	
	@PostMapping("/formCategoria")
	public String formCategoria(@Valid Categoria categoria, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
            return "crear/crearCategoria";
        }
		categoriaService.crearCategoria(categoria);
		
		return "listas/listaCategoria";
	}
	
	@GetMapping("/modificarCat/{id}")
	public String modificarCategoria(@SessionAttribute(name="categoria", required=false)Categoria categoria, @PathVariable Integer id, Model model) {
		categoria = categoriaService.obtenerCategoria(id);
		model.addAttribute("categoria",categoria);
		return "modificar/modificarCategoria";
	}
	@PostMapping("/modCategoria")
	public String mobCategoria(@Valid Categoria categoria, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
            return "modificar/modificarCategoria";
        }
		categoriaService.modificarCategoria(categoria);
		
		return "listas/listaCategoria";
	}
	
	@GetMapping("/eliminarCat/{id}")
	public String eliminarCategoria(@SessionAttribute(name= "categoria", required = false ) Categoria categoria  ,@PathVariable Integer id, Model model) {
		categoriaService.eliminarCategoria(id);
		return "listas/listaCategoria";
	}
	
	@GetMapping("/listaCategoria")
	public String listaCategoria(Model model) {
		return "listas/listaCategoria";
	}
	
	@ModelAttribute("categorias")
	public List<Categoria> categorias(){
		return categoriaService.obtenerCategorias();
	}
	
}
