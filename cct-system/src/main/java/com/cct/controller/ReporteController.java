package com.cct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cct.dto.ReporteDTO;

@Controller
public class ReporteController {

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void createReport(ReporteDTO reporteDTO){

	}

}
