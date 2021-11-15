package com.example.prueba1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba1.dao.LibroDAO;
import com.example.prueba1.models.domain.Libro;

@Service
public class LibroServiceImplementacion implements LibroService{

	@Autowired
	private LibroDAO libroDAO;

	// Obtiene todos los libros
	@Transactional
	@Override
	public List<Libro> findAll() {
		return (List<Libro>) libroDAO.findAll();
	}

	// Paginacion
	@Transactional(readOnly = true)
	@Override
	public Page<Libro> findAll(Pageable pageable){
		return libroDAO.findAll(pageable);
	}

	// Crear Libro
	@Transactional
	@Override
	public void save(Libro libro){
		libroDAO.save(libro);
	}

	// Obtiene un solo libro por id
	@Transactional(readOnly = true)
	@Override
	public Libro findOne(Long id) {
		return libroDAO.findById(id).orElse(null);
	}

	// Eliminar Libro
	@Transactional
	@Override
	public void delete(Libro libro) {
		libroDAO.delete(libro);
	}
}