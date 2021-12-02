package com.example.prueba1.services;

import java.util.List;

import com.example.prueba1.dao.UsuarioDAO;
import com.example.prueba1.models.domain.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImplementacion implements UsuarioService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
    @Autowired
	private UsuarioDAO usuarioDAO;

    @Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDAO.findAll();
	}

	// Paginacion
	@Transactional(readOnly = true)
	@Override
	public Page<Usuario> findAll(Pageable pageable){
		return usuarioDAO.findAll(pageable);
	}

	// Crear Autor
	@Transactional
	@Override
	public void save(Usuario usuario) {
		String password =  usuario.getPassword();
		String bcryptPassword  = passwordEncoder.encode(password);
		usuario.setPassword(bcryptPassword);
		usuarioDAO.save(usuario);
	}

	// Encuentra un Autor por id
	@Transactional
	@Override
	public Usuario findOne(Long id) {
		return usuarioDAO.findById(id).orElse(null);
	}

	// Elimina a un Autor
	@Transactional
	@Override
	public void delete(Long id) {
		usuarioDAO.deleteById(id);
	}

}