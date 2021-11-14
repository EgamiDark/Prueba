package com.example.prueba1.services;

import java.util.List;

import com.example.prueba1.models.domain.Autor;

public interface AutorService {
	public List<Autor> obtenerAutores();
	public Autor obtenerAutor(String id);
	public void eliminarAutor(String id);
	public void crearAutor(Autor autor);
	public void modificarAutor(Autor autor);
}
