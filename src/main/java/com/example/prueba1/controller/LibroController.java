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

import com.example.prueba1.editors.AutorEditor;
import com.example.prueba1.editors.CategoriaEditor;
import com.example.prueba1.models.domain.Autor;
import com.example.prueba1.models.domain.Categoria;
import com.example.prueba1.models.domain.Libro;
import com.example.prueba1.services.AutorService;
import com.example.prueba1.services.CategoriaService;
import com.example.prueba1.services.LibroService;

@Controller
@SessionAttributes("libro")
public class LibroController {
	@Autowired
	private LibroService libroService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private AutorService autorService;
	
	@Autowired
	private AutorEditor autorEditor;
	
	@Autowired
	private CategoriaEditor categoriaEditor;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Autor.class,"autor",autorEditor);
		binder.registerCustomEditor(Categoria.class,"categoria",categoriaEditor);
	}

	@GetMapping("/indexLibro")
	public String indexLibro() {
		return "contenido/contLibro";
	}
	
	@GetMapping("/crearLibro")
	public String crearLibro(Model model) {
		Libro libro = new Libro();
		model.addAttribute("libro",libro);
		return "crear/crearLibro";
	}
	
	@PostMapping("/formLibro")
	public String formLibro(@Valid Libro libro, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
            return "crear/crearLibro";
        }
		libroService.crearLibro(libro);
		
		return "listas/listaLibro";
	}
	
	@GetMapping("/modificarLib/{id}")
	public String modificarLibro(@SessionAttribute(name="libro", required=false)Libro libro, @PathVariable Integer id, Model model) {
		libro = libroService.obtenerLibro(id);
		model.addAttribute("libro",libro);
		
		return "modificar/modificarLibro";
	}
	@PostMapping("/modLibro")
	public String mobLibro(@Valid Libro libro, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
            return "modificar/modificarLibro";
        }
		libroService.modificarLibro(libro);
		
		return "listas/listaLibro";
	}
	
	@GetMapping("/eliminarLib/{id}")
	public String eliminarLibro(@SessionAttribute(name= "libro", required = false ) Libro libro  ,@PathVariable Integer id, Model model) {
		libroService.eliminarLibro(id);
		return "listas/listaLibro";
	}
	
	@GetMapping("/listaLibro")
	public String listaLibros(Model model) {
		return "listas/listaLibro";
	}
	
	@ModelAttribute("libros")
	public List<Libro> libros(){
		return libroService.obtenerLibros();
	}
	
	@ModelAttribute("autores")
	public List<Autor> autores(){
		return autorService.obtenerAutores();
	}
	
	@ModelAttribute("categorias")
	public List<Categoria> categorias(){
		return categoriaService.obtenerCategorias();
	}
	
}
