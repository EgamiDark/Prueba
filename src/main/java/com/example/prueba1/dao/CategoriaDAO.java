package com.example.prueba1.dao;

import com.example.prueba1.models.domain.Categoria;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoriaDAO extends PagingAndSortingRepository<Categoria, Long> {
    
}
