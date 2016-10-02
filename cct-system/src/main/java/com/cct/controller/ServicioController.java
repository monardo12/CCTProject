package com.cct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cct.services.ServicioService;

@Controller
public class ServicioController {

	@Autowired
	private ServicioService servicioService;
	
}
