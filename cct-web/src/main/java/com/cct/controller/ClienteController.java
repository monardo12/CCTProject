package com.cct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cct.model.Cliente;
import com.cct.services.ClienteService;

@Controller
@RequestMapping("/cliente")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/{idCliente}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> obtenerCliente(@PathVariable(value = "idCliente") Long idCliente){
		Cliente cliente = clienteService.obtenerCliente(idCliente);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
}
