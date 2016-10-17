package com.cct.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.cct.constant.EstadoReporte;
import com.cct.dto.ReporteDTO;
import com.cct.model.Reporte;
import com.cct.model.Usuario;
import com.cct.services.ReporteService;

@Component
public class AsyncReportProcessor {

	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private ReportProcessorFactory reportProcessorFactory;
	
	@JmsListener(destination = "report", containerFactory = "myFactory")
    public void receiveMessage(ReporteDTO reporteDTO) {
        System.out.println("Received <" + reporteDTO + ">");
        AbstractReportProcessor<?> reportProcessor = reportProcessorFactory.getReportProcessor(reporteDTO.getTipo());
		reportProcessor.createReport(reporteDTO);
		Reporte reporte = buildReporte(reporteDTO);
		reporte.setUrl("url generada async");
		reporteService.actualizarReporte(reporte);
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