package com.example.prueba1.dao;

import com.example.prueba1.models.domain.Autor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AutorDAO extends PagingAndSortingRepository<Autor, Long> {
    @Query("select a from Autor a where a.id like %?1%")
	public Autor findByStringId(String id);
}