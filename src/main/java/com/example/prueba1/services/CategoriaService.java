package com.example.prueba1.services;

import java.util.List;

import com.example.prueba1.models.domain.Autor;
import com.example.prueba1.models.domain.Categoria;

public interface CategoriaService {
	public List<Categoria> obtenerCategorias();
	public Categoria obtenerCategoria(Integer id);
	public void eliminarCategoria(Integer id);
	public void crearCategoria(Categoria categoria);
	public void modificarCategoria(Categoria categoria);
}
