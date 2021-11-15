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

import com.example.prueba1.editors.AutorEditor;
import com.example.prueba1.models.domain.Autor;
import com.example.prueba1.services.AutorService;
import com.example.prueba1.util.PageRender;

@Controller
@SessionAttributes("autor")
public class AutorController {
	@Autowired
	private AutorService autorService;
	@Autowired
	private AutorEditor autorEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Autor.class, "autor", autorEditor);
	}

	@RequestMapping(value = "indexAutor", method = RequestMethod.GET)
	public String indexAutor(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pager = PageRequest.of(page, 5);

		Page<Autor> autores = autorService.findAll(pager);
		PageRender<Autor> pageRender = new PageRender<>("/index", autores);

		model.addAttribute("titulo", "Listado de autores");
		model.addAttribute("autores", autores);
		model.addAttribute("page", pageRender);

		System.out.println(pageRender);

		return "contenido/contAutor";
	}

	@GetMapping("/crearAutor")
	public String crearAutor(Model model) {
		Autor autor = new Autor();
		model.addAttribute("autor", autor);
		return "crear/crearAutor";
	}

	@PostMapping("/formAutor")
	public String formAutor(@Valid Autor autor, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			return "crear/crearAutor";
		}

		String primerasTresLetras = autor.getNombre().substring(0, 3).toUpperCase();
		String anio = autor.getFechaNacimiento().substring(0, 4);
		String id = primerasTresLetras + anio;

		autor.setId(id);
		autorService.save(autor);

		return "redirect:indexAutor";
	}

	@GetMapping("/modificarAut/{id}")
	public String modificarAutor(@SessionAttribute(name = "autor", required = false) Autor autor,
			@PathVariable String id, Model model) {
		autor = autorService.findOne(id);
		model.addAttribute("autor", autor);
		return "modificar/modificarAutor";
	}

	/* @PostMapping("/modAutor")
	public String mobAutor(@Valid Autor autor, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
            return "modificar/modificarAutor";
        }
		autorService.modificarAutor(autor);
		
		return "listas/listaAutor";
	} */

	@GetMapping("/eliminarAutor/{id}")
	public String eliminarAutor(@SessionAttribute(name = "autor", required = false) Autor autor,
			@PathVariable String id, Model model) {		
		autorService.delete(id);
		setValues(0, model);
		return "contenido/contAutor";
	}

	public void setValues(int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Autor> autores = autorService.findAll(pageRequest);
        PageRender<Autor> pageRender = new PageRender<>("/indexAutor", autores);
        model.addAttribute("autores", autores);
        model.addAttribute("page", pageRender);
    }

	@GetMapping("/listaAutor")
	public String listaAutor(Model model) {
		return "listas/listaAutor";
	}

	@ModelAttribute("autores")
	public List<Autor> autores() {
		return autorService.findAll();
	}
}
