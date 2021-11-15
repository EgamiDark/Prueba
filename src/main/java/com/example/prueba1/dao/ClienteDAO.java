package com.example.prueba1.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.prueba1.models.domain.Cliente;

public interface ClienteDAO  extends PagingAndSortingRepository<Cliente, Long>{
	
	
	@Query("select c from Cliente c join fetch c.facturas f where c.id=?1")
	public Cliente getFacturasPorIdCliente(Long id);

}
