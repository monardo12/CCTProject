package com.cct.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cct.holder.RedisHolder;
import com.cct.model.Inventario;
import com.cct.services.InventarioService;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

private static final String ALL_INVENTARIO_KEY = "I";
	
	@Autowired
	private InventarioService inventarioService;
	
	@Autowired
	private RedisHolder<Inventario[]> inventarioHolder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Inventario>> obtenerInventario(){
		List<Inventario> inventario = inventarioService.obtenerInventarios();
		return new ResponseEntity<>(inventario, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cache/", method = RequestMethod.GET)
	public ResponseEntity<List<Inventario>> obtenerCacheInventarios(){
		Inventario[] inventarioArray = inventarioHolder.get(ALL_INVENTARIO_KEY, Inventario[].class);
		if(inventarioArray == null){
			List<Inventario> inventario = inventarioService.obtenerInventarios();
			inventarioArray = new Inventario[inventario.size()];
			inventarioArray = inventario.toArray(inventarioArray);
			inventarioHolder.add(inventarioArray, ALL_INVENTARIO_KEY);
		}
		return new ResponseEntity<>(Arrays.asList(inventarioArray), HttpStatus.OK);
	}
	
}
