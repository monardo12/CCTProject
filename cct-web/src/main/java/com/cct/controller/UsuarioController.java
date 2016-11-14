package com.cct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cct.model.Usuario;
import com.cct.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
@Secured("ROLE_ADMIN")
public class UsuarioController {
	
	@Autowired 
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<Usuario> crearUsuario(@RequestBody final Usuario usuario){
		Usuario usuario_ = usuarioService.crearUsuario(usuario);
		return new ResponseEntity<>(usuario_, HttpStatus.OK);
	}

}
