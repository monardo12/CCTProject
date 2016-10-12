package com.cct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cct.model.PlanVenta;
import com.cct.services.PlanVentaService;

@Controller
@RequestMapping("/planVenta")
public class PlanVentaController {
	
	@Autowired
	private PlanVentaService planVentaService;
	
	@RequestMapping(value = "/{idPlanVenta}", method = RequestMethod.GET)
	public ResponseEntity<PlanVenta> obtenerPlanVenta(@PathVariable(value = "idPlanVenta") Long idPlanVenta){
		PlanVenta planVenta = planVentaService.obtenerPlan(idPlanVenta);
		return new ResponseEntity<>(planVenta, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<PlanVenta>> getAllReportes(){
		List<PlanVenta> planesVenta = planVentaService.obtenerPlanes();
		return new ResponseEntity<>(planesVenta, HttpStatus.OK);
	}
	
}
