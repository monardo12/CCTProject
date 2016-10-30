package com.cct.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.cct.dto.ReporteDTO;

public abstract class AbstractReportProcessor<E> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractReportProcessor.class);
	
	public byte[] createReport(ReporteDTO reporteDTO){
		LOGGER.debug("Iniciando generación de reporte");
		byte[] output = null;
		try {
			Map<String, Object> map = new HashMap<>();
			List<E> listDatos = getInfo(reporteDTO);
	        DefaultTableModel dataReport = fillDataReport(listDatos);
	        JasperReport report = getFileReport();
		    JasperPrint print = fillReport(map, report,dataReport);
		    output = JasperExportManager.exportReportToPdf(print);
		    LOGGER.debug("Generación de reporte finalizada");
		} catch (JRException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, e.getMessage(), e);
		} catch (Exception e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, e.getMessage(), e);
		}	
		return output;
	}
	
	private JasperPrint fillReport(Map<String, Object> map,JasperReport report, DefaultTableModel tableModel) throws JRException{
		return JasperFillManager.fillReport(report, map, new JRTableModelDataSource(tableModel));
	}
	
	abstract JasperReport getFileReport() throws JRException;
	
	abstract List<E> getInfo(ReporteDTO datosConsulta);
	
	abstract DefaultTableModel fillDataReport(List<E> listData);
	
}
