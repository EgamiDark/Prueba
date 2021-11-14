package com.example.prueba1.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.prueba1.models.domain.Categoria;

@Service
public class CategoriaServiceImplementacion implements CategoriaService{
	
	private List<Categoria> categorias;
	
	public CategoriaServiceImplementacion() {
		
		this.categorias = new ArrayList<Categoria>(Arrays.asList(
				new Categoria(1, "Accion",null),
				new Categoria(2, "Terror",null),
				new Categoria(3, "Romantica",null),
				new Categoria(4, "Comedia",null)
				));
	}

	@Override
	public List<Categoria> obtenerCategorias() {
		// TODO Auto-generated method stub
		return this.categorias;
	}

	@Override
	public Categoria obtenerCategoria(Integer id) {
		Categoria result = null;
		for(Categoria categoria: this.categorias) {
			if(id==categoria.getId()) {
				result = categoria;
				break;
			}
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public void eliminarCategoria(Integer id) {
		// TODO Auto-generated method stub
		for(Categoria categoria: this.categorias) {
			if(id==categoria.getId()) {
				this.categorias.remove(categoria);
				break;
			}
		}
	}

	@Override
	public void crearCategoria(Categoria categoria) {
		this.categorias.add(categoria);
	}

	@Override
	public void modificarCategoria(Categoria categoria) {
		for(Categoria cat: this.categorias) {
			if(cat.getId()==categoria.getId()) {
				cat.setDescripcion(categoria.getDescripcion());
			}
		}
		
	}

}
