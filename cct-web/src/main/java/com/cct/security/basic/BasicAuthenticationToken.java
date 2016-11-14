package com.cct.security.basic;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class BasicAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	
	public BasicAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}
	
	public BasicAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	@Override
	public BasicCredentials getCredentials() {
		return (BasicCredentials) super.getCredentials();
	}
	
}
