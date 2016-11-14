package com.cct.security.basic;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

public class AuthenticationTokenFactory {
	
	private static final String AUTHENTICATION_BASIC_PREFIX = "Basic ";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationTokenFactory.class);
	
	private HttpServletRequest request;

	public AuthenticationTokenFactory(HttpServletRequest request) {
		this.request = request;
	}
	
	public Authentication buildAuthenticationToken() {
		Authentication authentication = null;
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (isBasicAuthentication(authorizationHeader)){
			LOGGER.debug("buildAuthenticationToken()--> authentication strategy: http basic");
			authentication = buildBasicAuthenticationToken(authorizationHeader);
		} else {
			LOGGER.debug("buildAuthenticationToken()--> invalid authentication type [{}]", authorizationHeader );
			throw new BadCredentialsException("Invalid username or password.");
		}
		return authentication;
	}
	
	private boolean isBasicAuthentication(String authorizationHeader) {
		return StringUtils.contains(authorizationHeader, AUTHENTICATION_BASIC_PREFIX);
	}
	
	private BasicAuthenticationToken buildBasicAuthenticationToken(String authorizationHeader) {

		String decodedCredentials = new String(Base64.decodeBase64(authorizationHeader.substring(AUTHENTICATION_BASIC_PREFIX.length())));
		String[] credentialsSplit = decodedCredentials.split(":");

		if (credentialsSplit.length < 2) {
			throw new BadCredentialsException("Invalid username or password.");
		}

		BasicCredentials credentials = new BasicCredentials(credentialsSplit[0], credentialsSplit[1]);

		return new BasicAuthenticationToken(StringUtils.EMPTY, credentials);
	}

}
