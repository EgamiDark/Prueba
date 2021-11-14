package com.example.prueba1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.prueba1.models.domain.Libro;

@Service
public class LibroServiceImplementacion implements LibroService{

	private List<Libro> libros;
	
	public LibroServiceImplementacion() {
		this.libros = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Libro> obtenerLibros() {
		// TODO Auto-generated method stub
		return this.libros;
	}

	@Override
	public Libro obtenerLibro(Integer id) {
		Libro result = null;
		for(Libro libro : this.libros) {
			if(id==libro.getId()) {
				result = libro;
				break;
			}
		}
		return result;
	}

	@Override
	public void eliminarLibro(Integer id) {
		// TODO Auto-generated method stub
		for(Libro libro: this.libros) {
			if(id ==libro.getId()) {
				this.libros.remove(libro);
				break;
			}
		}
	}

	@Override
	public void crearLibro(Libro libro) {
		this.libros.add(libro);
		
	}

	@Override
	public void modificarLibro(Libro libro) {
		for(Libro lib:this.libros) {
			if(libro.getId()==lib.getId()) {
				lib.setTitulo(libro.getTitulo());
				lib.setDescripcion(libro.getDescripcion());
				lib.setAnio(libro.getAnio());
				lib.setIsbn(libro.getIsbn());
				lib.setCantPaginas(libro.getCantPaginas());
				lib.setAutor(libro.getAutor());
				lib.setCategoria(libro.getCategoria());
			}
		}
		
	}

}
