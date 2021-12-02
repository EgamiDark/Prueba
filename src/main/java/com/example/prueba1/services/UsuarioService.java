package com.example.prueba1.services;

import java.util.List;

import com.example.prueba1.models.domain.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    public Page<Usuario> findAll(Pageable pageable);
	public List<Usuario> findAll();
	public void save(Usuario usuario);
	public Usuario findOne(Long id);
	public void delete(Long id);
}