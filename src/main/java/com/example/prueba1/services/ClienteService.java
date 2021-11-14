package com.example.prueba1.services;

import java.util.List;

import com.example.prueba1.models.domain.Autor;
import com.example.prueba1.models.domain.Cliente;

public interface ClienteService {
	
	public List<Cliente> findAll();
	public void save(Cliente cliente);
	public Cliente findOne(Long id);
	public void delete(Long id);
	
	
}