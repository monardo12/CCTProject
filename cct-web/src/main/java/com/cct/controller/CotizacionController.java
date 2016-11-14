package com.cct.controller;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cct.model.Cotizacion;
import com.cct.services.ClienteService;
import com.cct.services.CotizacionService;
import com.cct.services.ItemService;
import com.cct.services.ServicioService;

@Controller
@RequestMapping("/cotizacion")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class CotizacionController {

	@Autowired
	private CotizacionService cotizacionService;
	
	@Autowired
	private ServicioService servicioService;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/crear/{cotizacionPath}")
	@ResponseBody
	public String crearCotizacion(@PathVariable String[] cotizacionPath){		
		
		return cotizacionService.crearCotizacion(
			new Cotizacion(
				Long.parseLong(cotizacionPath[0]), 
				servicioService.obtenerServicio(Long.parseLong(cotizacionPath[1])), 
				clienteService.obtenerCliente(Long.parseLong(cotizacionPath[2])), 
				Arrays.asList(itemService.obtenerItem(Long.parseLong(cotizacionPath[3]))), 
				new Date(), 
				Integer.parseInt(cotizacionPath[4]), 
				"PENDIENTE", 
				0D, 0D
			)).getTotal()+"";
		
	}

}
