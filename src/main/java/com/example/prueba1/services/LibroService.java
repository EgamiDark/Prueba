package com.example.prueba1.services;

import java.util.List;

import com.example.prueba1.models.domain.Libro;

public interface LibroService {
	public List<Libro> obtenerLibros();
	public Libro obtenerLibro(Integer id);
	public void eliminarLibro(Integer id);
	public void crearLibro(Libro libro);
	public void modificarLibro(Libro libro);
}
