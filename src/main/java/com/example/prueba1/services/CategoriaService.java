package com.example.prueba1.services;

import java.util.List;

import com.example.prueba1.models.domain.Categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService {
	public Page<Categoria> findAll(Pageable pageable);
	public List<Categoria> findAll();
	public void save(Categoria categoria);
	public Categoria findOne(Long id);
	public void update(Categoria categoria);
	public void delete(Long id);
}
