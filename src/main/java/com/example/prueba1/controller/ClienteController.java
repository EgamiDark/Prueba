package com.example.prueba1.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.prueba1.models.domain.Cliente;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@GetMapping("/indexCliente")
	public String indexCliente() {
		return "contenido/contCliente";
	}
	
	@GetMapping("/crearCliente")
	public String crearCliente(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente",cliente);
		return "crear/crearCliente";
	}
	
	@PostMapping("/formCliente")
	public String formCliente(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
            return "crear/crearCliente";
        }
		
		return "listas/listaCliente";
	}
	
	
}
