package com.cct.report;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cct.dto.ReporteDTO;
import com.lowagie.text.pdf.PdfWriter;

public abstract class AbstractReportProcessor<E> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractReportProcessor.class);
	
	public byte[] createReport(ReporteDTO reporteDTO){
		LOGGER.debug("Iniciando generación de reporte");
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			Map<String, Object> map = new HashMap<>();
			List<E> listDatos = getInfo(reporteDTO);
	        DefaultTableModel dataReport = fillDataReport(listDatos);
	        JasperReport report = getFileReport();
		    JasperPrint print = fillReport(map, report,dataReport);
		    
		    JRPdfExporter exporter = new JRPdfExporter();
		    exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
		    
		    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		    configuration.setEncrypted(true);
		    configuration.set128BitKey(true);
		    configuration.setUserPassword("jasper");
		    configuration.setOwnerPassword("reports");
		    configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
		    exporter.setConfiguration(configuration);
		    exporter.exportReport();
		    LOGGER.debug("Generación de reporte finalizada");
		} catch (JRException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}	
		return output.toByteArray();
	}
	
	private JasperPrint fillReport(Map<String, Object> map,JasperReport report, DefaultTableModel tableModel) throws JRException{
		return JasperFillManager.fillReport(report, map, new JRTableModelDataSource(tableModel));
	}
	
	abstract JasperReport getFileReport() throws JRException;
	
	abstract List<E> getInfo(ReporteDTO datosConsulta);
	
	abstract DefaultTableModel fillDataReport(List<E> listData);
	
}
