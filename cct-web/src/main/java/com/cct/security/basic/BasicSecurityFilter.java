package com.cct.security.basic;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

public class BasicSecurityFilter extends GenericFilterBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(BasicSecurityFilter.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BasicAuthenticationEntryPoint authenticationEntryPoint;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		try {
			AuthenticationTokenFactory factory = new AuthenticationTokenFactory(request);
			Authentication authentication = factory.buildAuthenticationToken();
			Authentication successfulAuthentication = authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(successfulAuthentication);
			chain.doFilter(request, response);
		} catch(AuthenticationException authenticationException){
			LOGGER.error("authentication failed!");
			// If it fails clear this threads context and kick off the authentication entry point process.
			SecurityContextHolder.clearContext();
			authenticationEntryPoint.commence(request, response, authenticationException);	
		}				
	}
	
}
