package com.example.prueba1.services;

import java.util.List;

import com.example.prueba1.dao.RolDAO;
import com.example.prueba1.models.domain.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImplement implements RolService {
    @Autowired
	private RolDAO rolDAO;

    @Override
    public List<Rol> findAll() {
        return (List<Rol>) rolDAO.findAll();
    }
    
}