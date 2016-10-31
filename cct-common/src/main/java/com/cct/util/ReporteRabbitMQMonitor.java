package com.cct.util;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cct.dto.ReporteDTO;
import com.cct.dto.ReporteDTOQueue;
import com.cct.model.Reporte;
import com.cct.services.ReporteService;

@Component
public class ReporteRabbitMQMonitor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteRabbitMQMonitor.class);
	
	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private Connection rabbitConnection;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired 
	private RabbitTemplate amqpTemplate;
	
	@Autowired
	private ReporteQueueCacheUtil reporteQueueCacheUtil;
	
	private boolean isRabbitMQRunning = true;
	
	@Scheduled(fixedDelay=5000)
	private void monitorRabbitMQ(){
		if(rabbitConnection.isOpen()){
			LOGGER.info("RabbitMQ connection is open");
			if(!isRabbitMQRunning){
				isRabbitMQRunning = true;
				recoverAndPutReportsInRabbitMQ();
			}
		} else {			
			LOGGER.info("RabbitMQ connection is closed");
			if(isRabbitMQRunning){
				isRabbitMQRunning = false;
				recoverAndPutReportsInJMS();
			}
			
			//Try to recover RabbitMQ connection
			try{
				LOGGER.info("Trying to reopen RabbitMQ connection");
				rabbitConnection.createChannel(true);
				LOGGER.info("RabbitMQ connection reopened");
			} catch(Exception e){
				LOGGER.info("RabbitMQ connection could not be reopened");
			}
		}
	}
	
	private void recoverAndPutReportsInRabbitMQ(){
		ReporteDTOQueue reporteQueue = reporteQueueCacheUtil.getReporteQueueCache();
		
		//Recover messages from cache and send them to RabbitMQ
		for(ReporteDTO reporteDTO : reporteQueue.getReporteList().values()){
			amqpTemplate.convertAndSend(reporteDTO);
			System.out.println("Sent to RabbitMQ: <" + reporteDTO + ">");
		}
	}
	
	private void recoverAndPutReportsInJMS(){
		ReporteDTOQueue reporteQueue = reporteQueueCacheUtil.getReporteQueueCache();
		
		//Recover messages from cache and send them to JMS
		for(ReporteDTO reporteDTO : reporteQueue.getReporteList().values()){
			jmsTemplate.convertAndSend("report", reporteDTO);
			System.out.println("Sent to JMS: <" + reporteDTO + ">");
		}
	}
	
	public boolean isRabbitMQConnectionOpen(){
		return rabbitConnection.isOpen();
	}
	
	@Scheduled(fixedDelay=60000)
	private void recoverUnprocessedReports(){
		ReporteDTOQueue reporteQueue = reporteQueueCacheUtil.getReporteQueueCache();
		if(reporteQueue != null){
			List<Reporte> unprocessedReports = reporteService.obtenerReportesSinProcesar();
			for(Reporte reporte : unprocessedReports){
				if(!reporteQueue.getReporteList().containsKey(reporte.getIdReporte())){
					
					ReporteDTO reporteDTO = new ReporteDTO();
					reporteDTO.setIdUsuario(reporte.getUsuario().getIdUsuario());
					reporteDTO.setTipo(reporte.getTipo());
					reporteDTO.setId(reporte.getIdReporte());
					
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(0);
					cal.set(2010, Calendar.JANUARY, 1, 0, 0, 0);
					
					reporteDTO.setFechaInicial(cal.getTime());
					
					cal.set(2016, Calendar.OCTOBER, 30, 0, 0, 0);
					
					reporteDTO.setFechaFinal(cal.getTime());
					
					reporteQueueCacheUtil.addReporteToCacheQueue(reporteDTO);
					
					if(rabbitConnection.isOpen()){
						amqpTemplate.convertAndSend(reporteDTO);
						System.out.println("Sent to RabbitMQ: <" + reporteDTO + ">");
					} else {
						jmsTemplate.convertAndSend("report", reporteDTO);
						System.out.println("Sent to JMS: <" + reporteDTO + ">");
					}
				}
			}
		}
	}
	
}
