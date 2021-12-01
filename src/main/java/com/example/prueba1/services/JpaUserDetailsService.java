package com.example.prueba1.services;

import java.util.ArrayList;
import java.util.List;

import com.example.prueba1.dao.UsuarioDAO;
import com.example.prueba1.models.domain.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioDAO usuarioDao;

    private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            logger.error("Error login: no exiete el usuario '" + username + "' ");
            throw new UsernameNotFoundException("Username " + username + "no existe en el sistema!");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        /*
         * for(Rol role: usuario.getRoles()) {
         * logger.info("Role: ".concat(role.getAuthority()));
         * authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
         * 
         * }
         */

        if (authorities.isEmpty()) {
            logger.error("Error login:  usuario '" + username + "' no tienen roles asignados");
            throw new UsernameNotFoundException("Username  '" + username + "'no existe en el sistema!");
        }

        return new User(usuario.getNombreUsuario(),
                usuario.getContrasenia(),
                usuario.getActivo(),
                true,
                true,
                true,
                authorities);
    }
}