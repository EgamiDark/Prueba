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
import com.example.prueba1.models.domain.Autor;
import com.example.prueba1.services.AutorService;


@Controller
@SessionAttributes("autor")
public class AutorController {
	@Autowired
	private AutorService autorService;
	@Autowired
	private AutorEditor autorEditor;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Autor.class,"autor",autorEditor);
	}
	
	@GetMapping("/crearAutor")
	public String crearAutor(Model model) {
		Autor autor = new Autor();
		model.addAttribute("autor",autor);
		return "crear/crearAutor";
	}
	
	@PostMapping("/formAutor")
	public String formAutor(@Valid Autor autor, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
            return "crear/crearAutor";
        }
		autorService.crearAutor(autor);
		
		return "listas/listaAutor";
	}
	
	@GetMapping("/modificarAut/{id}")
	public String modificarAutor(@SessionAttribute(name="autor", required=false)Autor autor, @PathVariable String id, Model model) {
		autor = autorService.obtenerAutor(id);
		model.addAttribute("autor",autor);
		return "modificar/modificarAutor";
	}
	@PostMapping("/modAutor")
	public String mobAutor(@Valid Autor autor, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
            return "modificar/modificarAutor";
        }
		autorService.modificarAutor(autor);
		
		return "listas/listaAutor";
	}
	
	@GetMapping("/eliminarAut/{id}")
	public String eliminarAutor(@SessionAttribute(name= "autor", required = false ) Autor autor  ,@PathVariable String id, Model model) {
		autorService.eliminarAutor(id);
		return "listas/listaAutor";
	}
	
	@GetMapping("/listaAutor")
	public String listaAutor(Model model) {
		return "listas/listaAutor";
	}
	
	@ModelAttribute("autores")
	public List<Autor> autores(){
		return autorService.obtenerAutores();
	}
}
