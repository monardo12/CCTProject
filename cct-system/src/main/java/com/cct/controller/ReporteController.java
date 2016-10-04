package com.cct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cct.dto.ReporteDTO;
import com.cct.model.Reporte;
import com.cct.report.AbstractReportProcessor;
import com.cct.report.ReportProcessorFactory;
import com.cct.services.ReporteService;

@Controller
@RequestMapping("/reporte")
public class ReporteController {

	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private ReportProcessorFactory reportProcessorFactory;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<byte[]> createReport(@RequestBody ReporteDTO reporteDTO){
		AbstractReportProcessor<?> reportProcessor = reportProcessorFactory.getReportProcessor(reporteDTO.getTipo());
		byte[] report = reportProcessor.createReport(reporteDTO);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{idReporte}", method = RequestMethod.GET)
	public ResponseEntity<Reporte> getReporteById(@PathVariable("idReporte") Long idReporte){
		Reporte reporte = reporteService.obtenerReporte(idReporte);
		return new ResponseEntity<>(reporte, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Reporte>> getAllReportes(){
		List<Reporte> reportes = reporteService.obtenerReportes();
		return new ResponseEntity<>(reportes, HttpStatus.OK);
	}

}
