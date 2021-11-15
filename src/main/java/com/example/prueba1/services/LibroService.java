package com.example.prueba1.services;

import java.util.List;

import com.example.prueba1.models.domain.Libro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibroService {
	public Page<Libro> findAll(Pageable pageable);
	public List<Libro> findAll();
	public void save(Libro libro);
	public Libro findOne(Long id);
	public void delete(Libro libro);
}
