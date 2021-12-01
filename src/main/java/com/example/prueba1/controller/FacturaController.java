package com.example.prueba1.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.prueba1.models.domain.*;
import com.example.prueba1.services.*;

@Controller
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/verFacturas/{id}")
	public String verFacturas(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.findOne(id);
		List<Factura> facturas = facturaService.getFacturasPorId(cliente);
		
		model.addAttribute("facturas",facturas);
		model.addAttribute("cliente",cliente);
		return "factura/verFacturas";
	}
	
	@GetMapping("/verDetalle/{id}")
	public String verDetalle(@PathVariable Long id, Model model) {
		
		List<DetalleFactura> detalleFactura = facturaService.findOne(id).getDetalles();
		Factura factura = facturaService.findOne(id);
		model.addAttribute("factura",factura);
		model.addAttribute("detallesFactura",detalleFactura);
		return "factura/verDetalleFactura";
	}
	
	@GetMapping("/crearFactura/{id}")
	public String crearFactura(@PathVariable Long id, Model model) {
		
		Factura factura = new Factura();
		Cliente cliente = clienteService.findOne(id);
		model.addAttribute("factura",factura);
		model.addAttribute("cliente",cliente);
		return "crear/crearFactura";
	}
	
	@PostMapping("/formFactura")
	public String formFactura(@Valid Factura factura,BindingResult result, Model model, SessionStatus status,
			@RequestParam(name="item_id[]", required = false) Long[] itemId,
			@RequestParam(name="cantidad[]", required = false) Integer[] cantidad,
			@RequestParam(name="totalDetalle[]", required = false) Double[] totalDetalle) {
		Long idCliente = factura.getCliente().getId();
		if (result.hasErrors()) {
			Cliente cliente = clienteService.findOne(idCliente);
			model.addAttribute("cliente",cliente);
			model.addAttribute("factura",factura);
            return "crear/crearFactura";
        }
		List<DetalleFactura> listDetalle = new ArrayList();
		for(int i= 0; i< itemId.length; i++ ) {
			Libro libro = libroService.findOne(itemId[i]);
			
			DetalleFactura uno = new DetalleFactura();
			uno.setCantidad(cantidad[i]);
			uno.setLibro(libro);
			uno.setTotalDetalle(totalDetalle[i]);
			listDetalle.add(uno);
		}
		
		factura.setDetalles(listDetalle);
		LocalDate fecha = LocalDate.now();
		factura.setFechaIngreso(fecha.toString());
		
		facturaService.save(factura);
		return "redirect:indexCliente";
	}
	
	@GetMapping(value="/cargar_libros/{term}", produces= {"application/json"})
	public @ResponseBody List<Libro> cargarLibros(@PathVariable String term){
		
		List<Libro> libros = facturaService.findByNombre(term);
		List<Libro> newLibros = new ArrayList();
		for(Libro libro : libros) {
			Libro lib = new Libro();
			lib.setId(libro.getId());
			lib.setTitulo(libro.getTitulo());
			lib.setPrecio(libro.getPrecio());
			newLibros.add(lib);
		}
		
		return newLibros;
	}
	
	@GetMapping(value="/confirmarFacturas/{id}", produces= {"application/json"})
	public @ResponseBody Integer confirmarFacturas(@PathVariable Long id) {
		Cliente cliente = clienteService.findOne(id);
		List<Factura> facturas = facturaService.getFacturasPorId(cliente);
		if(facturas.size()>=1) {
			return 1;
		}
		return 0;
	}
	
	
	
}
