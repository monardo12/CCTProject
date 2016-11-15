package com.cct.report;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.cct.aws.AmazonS3Service;
import com.cct.constant.EstadoReporte;
import com.cct.dto.ReporteDTO;
import com.cct.model.Reporte;
import com.cct.model.Usuario;
import com.cct.services.JwtUserDetailsService;
import com.cct.services.ReporteService;
import com.cct.util.MailSender;
import com.cct.util.ReporteQueueCacheUtil;

@Component
public class AsyncReportProcessor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AsyncReportProcessor.class);

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private ReportProcessorFactory reportProcessorFactory;

	@Autowired
	private ReporteQueueCacheUtil reporteQueueCacheUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private AmazonS3Service amazonS3Service;

	@JmsListener(destination = "report", containerFactory = "myFactory")
    public void receiveMessage(ReporteDTO reporteDTO) {
		LOGGER.info("Received <" + reporteDTO + ">");
        if(reporteQueueCacheUtil.isReporteInCacheQueue(reporteDTO)){

        	String reportUrl = amazonS3Service.buildReportUrl(reporteDTO);
        	reporteDTO.setUrl(reportUrl);

        	AbstractReportProcessor<?> reportProcessor = reportProcessorFactory.getReportProcessor(reporteDTO.getTipo());
    		byte[] reportAsBytes = reportProcessor.createReport(reporteDTO);
    		String md5 = DigestUtils.md5Hex(reportAsBytes);

    		amazonS3Service.uploadReporte(reporteDTO, reportAsBytes);

    		Reporte reporte = buildReporte(reporteDTO);
    		reporte.setUrl(reportUrl);
    		reporte.setMd5(md5);

    		reporteService.actualizarReporte(reporte);

//    		mailSender.sendEmail(
//    				jwtUserDetailsService.loadUserDetailsFromSecurityContextHolder().getEmail(),
//    				reportAsBytes, reporte);

    		reporteQueueCacheUtil.deleteReporteFromCacheQueue(reporteDTO);
        }
    }

	private Reporte buildReporte(ReporteDTO reporteDTO) {
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