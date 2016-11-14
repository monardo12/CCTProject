package com.cct.security;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority{
	
	ADMIN,
	
	USER;
	
	@Override
	public String getAuthority() {
		return "ROLE_" + name();
	}

}
