package com.cct.controller;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cct.config.RabbitConfig;
import com.cct.constant.EstadoReporte;
import com.cct.dto.ReporteDTO;
import com.cct.model.Reporte;
import com.cct.model.Usuario;
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
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Reporte> createAsyncReport(@RequestBody ReporteDTO reporteDTO){
		Reporte reporte = buildReporte(reporteDTO);
		reporte.setEstado(EstadoReporte.EN_PROCESO);
		Reporte newReporte = reporteService.crearReporte(reporte);
		reporteDTO.setId(newReporte.getIdReporte());
		
		//Sending to queue
		ApplicationContext context = new AnnotationConfigApplicationContext(RabbitConfig.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        amqpTemplate.convertAndSend(reporteDTO);

        System.out.println("Sent to RabbitMQ: <" + reporteDTO + ">");
		
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
		
		System.out.println("Sent: <" + reporteDTO + ">");
		
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
