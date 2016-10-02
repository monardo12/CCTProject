package com.cct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cct.services.SocioService;

@Controller
public class SocioController {

	@Autowired
	private SocioService socioService;
	
}
