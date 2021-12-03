package com.example.prueba1.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.prueba1.models.domain.Rol;
import com.example.prueba1.models.domain.Usuario;
import com.example.prueba1.services.RolService;
import com.example.prueba1.services.UsuarioService;
import com.example.prueba1.util.PageRender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RolService rolService;

	@RequestMapping(value = "indexUsuario", method = RequestMethod.GET)
	public String indexUsuario(@RequestParam(name = "page", defaultValue = "0") int page,
			Model model) {
		setValues(page, model);

		return "contenido/contUsuario";
	}

	@GetMapping("/crearUsuario")
	public String crearUsuario(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "crear/crearUsuario";
	}

	@PostMapping("/formUsuario")
	public String formUsuario(@Valid Usuario usuario,
			BindingResult result,
			Model model,
			SessionStatus status) {
		Usuario newUsuario = usuarioService.findByUserName(usuario.getUsername());
		if(newUsuario != null) {
			model.addAttribute("ErrorUser", "Ya existe un usuario con este UserName");
			return "crear/crearUsuario";
		}
		if (result.hasErrors()) {
			return "crear/crearUsuario";
		}
		usuario.setEnable(true);

		usuarioService.save(usuario);

		return "redirect:indexUsuario";
	}

	@GetMapping("/modificarUsuario/{id}")
	public String modificarUsuario(@SessionAttribute(name = "usuario", required = false) Usuario usuario,
			@PathVariable Long id, Model model) {
		usuario = usuarioService.findOne(id);
		model.addAttribute("usuario", usuario);
		return "modificar/modificarUsuario";
	}

	@PostMapping("/modUsuario")
	public String modUsuario(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			return "modificar/modificarUsuario";
		}
		

		usuarioService.update(usuario);

		return "redirect:indexUsuario";
	}

	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(@SessionAttribute(name = "usuario", required = false) Usuario usuario,
			@PathVariable Long id, Model model) {
		usuarioService.delete(id);
		setValues(0, model);
		return "contenido/contUsuario";
	}

	public void setValues(int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Usuario> usuarios = usuarioService.findAll(pageRequest);
		PageRender<Usuario> pageRender = new PageRender<>("/indexAutor", usuarios);
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("page", pageRender);
	}

	@ModelAttribute("roles")
	public List<Rol> roles() {
		return rolService.findAll();
	}
}