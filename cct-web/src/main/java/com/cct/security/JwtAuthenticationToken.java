package com.cct.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.cct.security.basic.BasicCredentials;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	
	public JwtAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}
	
	public JwtAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	@Override
	public BasicCredentials getCredentials() {
		return (BasicCredentials) super.getCredentials();
	}
	
}
