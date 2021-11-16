package com.example.prueba1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.prueba1.models.domain.*;

public interface FacturaDAO extends PagingAndSortingRepository<Factura, Long>{
	
	
	@Query("select f from Factura f where f.cliente=?1")
	public List<Factura> getFacturasPorIdCliente(Cliente cliente);

}
