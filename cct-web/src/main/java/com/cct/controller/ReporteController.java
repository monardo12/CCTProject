package com.cct.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cct.constant.EstadoReporte;
import com.cct.dto.ReporteDTO;
import com.cct.model.Reporte;
import com.cct.model.Usuario;
import com.cct.report.AbstractReportProcessor;
import com.cct.report.ReportProcessorFactory;
import com.cct.services.ReporteService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/reporte")
public class ReporteController {

	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private ReportProcessorFactory reportProcessorFactory;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired 
	private RabbitTemplate amqpTemplate;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Reporte> createAsyncReport(@RequestBody ReporteDTO reporteDTO){
		Reporte reporte = buildReporte(reporteDTO);
		reporte.setEstado(EstadoReporte.EN_PROCESO);
		Reporte newReporte = reporteService.crearReporte(reporte);
		reporteDTO.setId(newReporte.getIdReporte());
		
		//Sending to queue		     
        amqpTemplate.convertAndSend(reporteDTO);

        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Sent to RabbitMQ: <" + reporteDTO + ">");
		
		return new ResponseEntity<>(newReporte, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/jms", method = RequestMethod.POST)
	public ResponseEntity<Reporte> createJmsAsyncReport(@RequestBody ReporteDTO reporteDTO){
		Reporte reporte = buildReporte(reporteDTO);
		reporte.setEstado(EstadoReporte.EN_PROCESO);
		Reporte newReporte = reporteService.crearReporte(reporte);
		reporteDTO.setId(newReporte.getIdReporte());

		//Sending message
		jmsTemplate.convertAndSend("report", reporteDTO);
		
		Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Sent: <" + reporteDTO + ">");
		
		return new ResponseEntity<>(newReporte, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/direct", method = RequestMethod.POST)
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
	
	private Reporte buildReporte(ReporteDTO reporteDTO){
		Reporte reporte = new Reporte();
		reporte.setEstado(EstadoReporte.GENERADO);
		reporte.setTipo(reporteDTO.getTipo());
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(reporteDTO.getIdUsuario());
		reporte.setUsuario(usuario);
		return reporte;
	}

}
