package com.cct.security;

import java.util.Arrays;

import com.cct.model.Usuario;

public final class JwtUserDetailsFactory {

	public static JwtUserDetails create(Usuario usuario) {
        return new JwtUserDetails(
        		usuario.getIdUsuario(),
        		usuario.getUsername(),
        		usuario.getNombre(),
        		usuario.getEmail(),
        		usuario.getPassword(),
        		usuario.getPrivateKey(),
                Arrays.asList(usuario.getRole())
        );
    }
	
}
