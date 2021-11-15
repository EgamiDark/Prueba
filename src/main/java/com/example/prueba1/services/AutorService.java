package com.example.prueba1.services;

import java.util.List;

import com.example.prueba1.models.domain.Autor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AutorService {
	public Page<Autor> findAll(Pageable pageable);
	public List<Autor> findAll();
	public void save(Autor autor);
	public Autor findOne(String id);
	public void delete(String id);
}
