package com.cct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cct.model.Inventario;
import com.cct.services.InventarioService;

@Controller
@RequestMapping("/inventario")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class InventarioController {

	@Autowired
	private InventarioService inventarioService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Inventario>> obtenerInventario(){
		List<Inventario> inventario = inventarioService.obtenerInventarios();
		return new ResponseEntity<>(inventario, HttpStatus.OK);
	}
	
}
