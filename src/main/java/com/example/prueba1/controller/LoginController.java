package com.example.prueba1.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String ingresar(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			Model model,
			Principal principal,
			RedirectAttributes flash) {

		if (principal != null) {

			flash.addFlashAttribute("info", "ya ha iniciado sesion");
			return "redirect:/index";
		}

		if (error != null) {
			model.addAttribute("error", "Error en login: nombre o pass no validos");

		}

		if (logout != null) {
			model.addAttribute("success", "ha cerrado sesion con exito");
		}

		return "contenido/contLogin";
	}

}