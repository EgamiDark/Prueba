package com.example.prueba1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba1.dao.*;
import com.example.prueba1.models.domain.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class FacturaServiceImplementacion implements FacturaService {

	@Autowired
	private FacturaDAO facturaDao;
	
	@Autowired
	private LibroDAO libroDao;
	
	@Autowired
	private ClienteDAO clienteDao;

	@Transactional(readOnly = true)
	@Override
	public List<Factura> findAll() {

		return (List<Factura>) facturaDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Factura> findAll(Pageable pageable) {

		return facturaDao.findAll(pageable);
	}


	@Transactional
	@Override
	public void save(Factura factura) {
		facturaDao.save(factura);

	}

	@Transactional(readOnly = true)
	@Override
	public Factura findOne(Long id) {

		return facturaDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		facturaDao.deleteById(id);

	}
	
	@Transactional
	@Override
	public List<Libro> findByNombre(String term) {
		
		return libroDao.findByNombre(term);

	}
	
	@Transactional
	@Override
	public List<Factura> getFacturasPorId(Cliente cliente) {
		
		return facturaDao.getFacturasPorIdCliente(cliente);

	}

}
