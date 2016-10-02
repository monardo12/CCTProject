package com.cct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cct.services.PlanVentaService;

@Controller
public class PlanVentaController {

	@Autowired
	private PlanVentaService planVentaService;
	
}
