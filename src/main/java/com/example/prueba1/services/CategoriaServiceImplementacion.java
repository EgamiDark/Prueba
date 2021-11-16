package com.example.prueba1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba1.dao.CategoriaDAO;
import com.example.prueba1.models.domain.Categoria;

@Service
public class CategoriaServiceImplementacion implements CategoriaService{
	@Autowired
	private CategoriaDAO categoriaDAO;

	// Paginacion
	@Transactional(readOnly = true)
	@Override
	public Page<Categoria> findAll(Pageable pageable){
		return categoriaDAO.findAll(pageable);
	}

	@Transactional
	@Override
	public List<Categoria> findAll() {
		return (List<Categoria>) categoriaDAO.findAll();
	}

	@Transactional
	@Override
	public void save(Categoria categoria) {
		categoriaDAO.save(categoria);
	}

	@Transactional
	@Override
	public Categoria findOne(Long id) {
		return categoriaDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void update(Categoria categoria){
		categoriaDAO.save(categoria);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Categoria categoriaDelete = categoriaDAO.findById(id).orElse(null);
		categoriaDAO.delete(categoriaDelete);
	}
}
