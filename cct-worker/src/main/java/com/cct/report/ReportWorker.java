package com.cct.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ErrorHandler;

import com.cct.config.ApplicationConfig;
import com.cct.config.RabbitConfig;
import com.cct.constant.EstadoReporte;
import com.cct.dto.ReporteDTO;
import com.cct.model.Reporte;
import com.cct.model.Usuario;
import com.cct.services.ReporteService;
import com.cct.util.ReporteQueueCacheUtil;

public class ReportWorker {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportWorker.class);
	
	private ReportWorker() {
		// No requiere inicialización
	}

	public static void main(String[] args) {
        final ApplicationContext rabbitConfig = new AnnotationConfigApplicationContext(RabbitConfig.class);
        final ConnectionFactory rabbitConnectionFactory = rabbitConfig.getBean(ConnectionFactory.class);
        final Queue rabbitQueue = rabbitConfig.getBean(Queue.class);
        final MessageConverter messageConverter = new SimpleMessageConverter();
        
        final ApplicationContext applicationConfig = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        final ReporteService reporteService = applicationConfig.getBean(ReporteService.class);
        final ReportProcessorFactory reportProcessorFactory = applicationConfig.getBean(ReportProcessorFactory.class);
        final ReporteQueueCacheUtil reporteQueueCacheUtil = applicationConfig.getBean(ReporteQueueCacheUtil.class);
        
        // create a listener container, which is required for asynchronous message consumption.
        // AmqpTemplate cannot be used in this case
        final SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(rabbitConnectionFactory);
        listenerContainer.setQueueNames(rabbitQueue.getName());

        // set the callback for message handling
        listenerContainer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                final ReporteDTO reporteDTO = (ReporteDTO) messageConverter.fromMessage(message);
                LOGGER.info("Received <" + reporteDTO + ">");
                if(reporteQueueCacheUtil.isReporteInCacheQueue(reporteDTO)){
                	AbstractReportProcessor<?> reportProcessor = reportProcessorFactory.getReportProcessor(reporteDTO.getTipo());
            		byte[] reportAsBytes = reportProcessor.createReport(reporteDTO);
            		String md5 = DigestUtils.md5Hex(reportAsBytes);
            		Reporte reporte = buildReporte(reporteDTO);
            		reporte.setUrl("RabbitMQ");
            		reporte.setMd5(md5);
            		reporteService.actualizarReporte(reporte);
            		reporteQueueCacheUtil.deleteReporteFromCacheQueue(reporteDTO);
                }
            }
        });

        // set a simple error handler
        listenerContainer.setErrorHandler(new ErrorHandler() {
            public void handleError(Throwable t) {
            	LOGGER.info(t.getMessage(), t);
            }
        });

        // register a shutdown hook with the JVM
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
            	LOGGER.info("Shutting down ReportWorker");
                listenerContainer.shutdown();
            }
        });

        // start up the listener. this will block until JVM is killed.
        listenerContainer.start();
        LOGGER.info("ReportWorker started");
    }
	
	private static Reporte buildReporte(ReporteDTO reporteDTO){
		Reporte reporte = new Reporte();
		reporte.setIdReporte(reporteDTO.getId());
		reporte.setEstado(EstadoReporte.GENERADO);
		reporte.setTipo(reporteDTO.getTipo());
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(reporteDTO.getIdUsuario());
		reporte.setUsuario(usuario);
		return reporte;
	}
}
