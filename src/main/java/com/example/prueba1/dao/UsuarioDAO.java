package com.example.prueba1.dao;

import com.example.prueba1.models.domain.Usuario;

public interface UsuarioDAO {
    public Usuario findByUsername(String username);
}