package com.example.prueba1.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.prueba1.models.domain.*;

public interface DetalleFacturaDAO extends PagingAndSortingRepository<DetalleFactura, Long>{

}
