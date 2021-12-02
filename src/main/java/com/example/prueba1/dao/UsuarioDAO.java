package com.example.prueba1.dao;

import com.example.prueba1.models.domain.Usuario;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long>  {
    public Usuario findByUsername(String username);
}