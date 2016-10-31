package com.cct.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.cct.constant.EstadoReporte;
import com.cct.dto.ReporteDTO;
import com.cct.model.Reporte;
import com.cct.model.Usuario;
import com.cct.services.ReporteService;
import com.cct.util.ReporteQueueCacheUtil;

@Component
public class AsyncReportProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncReportProcessor.class);
	
	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private ReportProcessorFactory reportProcessorFactory;
	
	@Autowired
	private ReporteQueueCacheUtil reporteQueueCacheUtil;
	
	@JmsListener(destination = "report", containerFactory = "myFactory")
    public void receiveMessage(ReporteDTO reporteDTO) {
		LOGGER.info("Received <" + reporteDTO + ">");
        if(reporteQueueCacheUtil.isReporteInCacheQueue(reporteDTO)){
        	AbstractReportProcessor<?> reportProcessor = reportProcessorFactory.getReportProcessor(reporteDTO.getTipo());
    		reportProcessor.createReport(reporteDTO);
    		Reporte reporte = buildReporte(reporteDTO);
    		reporte.setUrl("JMS");
    		reporteService.actualizarReporte(reporte);
    		reporteQueueCacheUtil.deleteReporteFromCacheQueue(reporteDTO);
        }
    }
	
	private Reporte buildReporte(ReporteDTO reporteDTO){
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