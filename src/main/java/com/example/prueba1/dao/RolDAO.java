package com.example.prueba1.dao;

import com.example.prueba1.models.domain.Rol;

import org.springframework.data.repository.CrudRepository;

public interface RolDAO extends CrudRepository<Rol, Long>  {
    
}