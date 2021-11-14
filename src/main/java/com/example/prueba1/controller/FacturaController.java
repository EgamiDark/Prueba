package com.example.prueba1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("factura")
public class FacturaController {

	@GetMapping("/indexFactura")
	public String indexFactura() {
		return "contenido/contFactura";
	}
	
}
