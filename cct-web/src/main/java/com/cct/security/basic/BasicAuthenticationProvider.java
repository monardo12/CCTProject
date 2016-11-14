package com.cct.security.basic;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.cct.model.Usuario;
import com.cct.repo.UsuarioRepository;
import com.cct.security.UserRole;

@Component
public class BasicAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		BasicAuthenticationToken restToken = (BasicAuthenticationToken) authentication;
		BasicCredentials credentials = restToken.getCredentials();
		
		Usuario usuario = usuarioRepository.findByPrivateKeyAndPrivateLogin(credentials.getPrivateLogin(), credentials.getPrivateKey());
		
		if (usuario == null) {
			throw new BadCredentialsException("Invalid username or password.");
		}
		
		Collection<? extends GrantedAuthority> authorities = buildAuthoritiesForUsuario(usuario);
		return new BasicAuthenticationToken(usuario.getPublicKey(), credentials, authorities);
	}

	private Collection<? extends GrantedAuthority> buildAuthoritiesForUsuario(Usuario usuario){
		Collection<UserRole> usuarioAuthorities = new ArrayList<UserRole>();
		usuarioAuthorities.add(usuario.getRole());
		return usuarioAuthorities;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return BasicAuthenticationToken.class.equals(authentication);
	}

}
