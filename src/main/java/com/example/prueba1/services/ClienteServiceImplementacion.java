package com.example.prueba1.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba1.dao.*;
import com.example.prueba1.models.domain.*;

@Service
public class ClienteServiceImplementacion implements ClienteService{

	@Autowired
	private ClienteDAO clienteDao;

	// Obtener todos los clientes
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {

		return (List<Cliente>) clienteDao.findAll();
	}

	// Obtener todos los clientes por pagina
	@Transactional(readOnly = true)
	@Override
	public Page<Cliente> findAll(Pageable pageable) {

		return clienteDao.findAll(pageable);
	}

	// Crear cliente
	@Transactional
	@Override
	public void save(Cliente cliente) {
		LocalDate fecha = LocalDate.now();
		cliente.setCreate_at(fecha.toString());

		clienteDao.save(cliente);

	}

	// Obtener cliente por id
	@Transactional(readOnly = true)
	@Override
	public Cliente findOne(Long id) {

		return clienteDao.findById(id).orElse(null);
	}

	// Eliminar Cliente
	@Transactional
	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);

	}
	
}