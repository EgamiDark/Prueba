package com.example.prueba1.dao;


import com.example.prueba1.models.domain.Libro;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface LibroDAO extends PagingAndSortingRepository<Libro, Long> {
    
    
}