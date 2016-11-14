package com.cct.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.access.annotation.Secured;
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
import com.cct.util.ReporteQueueCacheUtil;
import com.cct.util.ReporteRabbitMQMonitor;

@Controller
@RequestMapping("/reporte")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class ReporteController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteController.class);

	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private ReportProcessorFactory reportProcessorFactory;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired 
	private RabbitTemplate amqpTemplate;
	
	@Autowired
	private ReporteQueueCacheUtil reporteQueueCacheUtil;
	
	@Autowired
	private ReporteRabbitMQMonitor reporteRabbitMQMonitor;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Reporte> createAsyncReport(@RequestBody ReporteDTO reporteDTO){
		Reporte reporte = buildReporte(reporteDTO);
		reporte.setEstado(EstadoReporte.EN_PROCESO);
		
		Reporte newReporte = reporteService.crearReporte(reporte);
		reporteDTO.setId(newReporte.getIdReporte());
		
		//Sending to queue		     
    	sendReportToQueue(reporteDTO);
		
		return new ResponseEntity<>(newReporte, HttpStatus.OK);
	}
	
	public void sendReportToQueue(ReporteDTO reporteDTO){
		reporteQueueCacheUtil.addReporteToCacheQueue(reporteDTO);
		
		if(reporteRabbitMQMonitor.isRabbitMQConnectionOpen()){
			amqpTemplate.convertAndSend(reporteDTO);
			LOGGER.info("Sent to RabbitMQ: <" + reporteDTO + ">");
		} else {
			jmsTemplate.convertAndSend("report", reporteDTO);
			LOGGER.info("Sent to JMS: <" + reporteDTO + ">");
		}
	}
	
	@RequestMapping(value = "/rabbitmq", method = RequestMethod.POST)
	public ResponseEntity<Reporte> createRabbitMQAsyncReport(@RequestBody ReporteDTO reporteDTO){
		Reporte reporte = buildReporte(reporteDTO);
		reporte.setEstado(EstadoReporte.EN_PROCESO);
		Reporte newReporte = reporteService.crearReporte(reporte);
		reporteDTO.setId(newReporte.getIdReporte());
		
		//Sending to queue		     
        amqpTemplate.convertAndSend(reporteDTO);

        LOGGER.info("Sent to RabbitMQ: <" + reporteDTO + ">");
		
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
		
		LOGGER.info("Sent to JMS: <" + reporteDTO + ">");
		
		return new ResponseEntity<>(newReporte, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/direct", method = RequestMethod.POST)
	public void createReport(@RequestBody ReporteDTO reporteDTO, HttpServletResponse response){
		Reporte reporte = buildReporte(reporteDTO);
		reporte.setEstado(EstadoReporte.GENERADO);
		reporte.setUrl("direct");
		
		Reporte newReporte = reporteService.crearReporte(reporte);
		reporteDTO.setId(newReporte.getIdReporte());
		
		AbstractReportProcessor<?> reportProcessor = reportProcessorFactory.getReportProcessor(reporteDTO.getTipo());
		byte[] reportAsBytes = reportProcessor.createReport(reporteDTO);
		
		try {
			OutputStream output = response.getOutputStream();
			output.write(reportAsBytes);
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			response.flushBuffer();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
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
