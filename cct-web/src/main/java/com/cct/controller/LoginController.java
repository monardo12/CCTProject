package com.cct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cct.dto.AuthenticationRequest;
import com.cct.security.JwtAuthenticationResponse;
import com.cct.services.AuthenticationService;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest)
			throws AuthenticationException {
		
		String token = authenticationService.authenticate(authenticationRequest);

		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

}
