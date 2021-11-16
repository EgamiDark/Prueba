package com.example.prueba1.dao;


import com.example.prueba1.models.domain.Libro;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LibroDAO extends PagingAndSortingRepository<Libro, Long> {
    
	@Query("select l from Libro l where l.titulo like %?1%")
	public List<Libro> findByNombre(String term);
	
}