package com.cct.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cct.model.Usuario;
import com.cct.repo.UsuarioRepository;
import com.cct.security.JwtUserDetails;
import com.cct.security.JwtUserDetailsFactory;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserDetailsFactory.create(usuario);
        }
    }
    
    public JwtUserDetails loadUserDetailsFromSecurityContextHolder(){
    	return (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}