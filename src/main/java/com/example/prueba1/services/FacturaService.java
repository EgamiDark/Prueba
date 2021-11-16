package com.example.prueba1.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.prueba1.models.domain.*;

public interface FacturaService {
	
	public List<Factura> findAll();
	public Page<Factura> findAll(Pageable pageable);
	public void save(Factura factura);
	public Factura findOne(Long id);
	public void delete(Long id);
	public List<Libro> findByNombre(String term);
	public List<Factura> getFacturasPorId(Cliente cliente);
	
	
}
