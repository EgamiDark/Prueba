package com.example.prueba1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba1.dao.AutorDAO;
import com.example.prueba1.models.domain.Autor;

@Service
public class AutorServiceImplementacion implements AutorService{

	@Autowired
	private AutorDAO autorDAO;

	public AutorServiceImplementacion() {}

	@Override
	public List<Autor> findAll() {
		return (List<Autor>) autorDAO.findAll();
	}

	// Paginacion
	@Transactional(readOnly = true)
	@Override
	public Page<Autor> findAll(Pageable pageable){
		return autorDAO.findAll(pageable);
	}

	// Crear Autor
	@Transactional
	@Override
	public void save(Autor autor) {
		autorDAO.save(autor);
	}

	// Encuentra un Autor por id
	@Transactional
	@Override
	public Autor findOne(String id) {
		return autorDAO.findByStringId(id);
	}

	// Elimina a un Autor
	@Transactional
	@Override
	public void delete(String id) {
		Autor autor = autorDAO.findByStringId(id);
		System.out.println("============================================================");
		System.out.println(autor);
		System.out.println("============================================================");
		autorDAO.delete(autor);
	}

}
