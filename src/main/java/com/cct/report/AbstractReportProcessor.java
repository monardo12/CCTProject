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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cct.dto.ReporteDTO;

public abstract class AbstractReportProcessor<E> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractReportProcessor.class);
	
	public byte[] createReport(ReporteDTO reporteDTO){
		byte[] output = null;
		try {
			Map<String, Object> map = new HashMap<>();
			List<E> listDatos = getInfo(reporteDTO);
	        DefaultTableModel dataReport = fillDataReport(listDatos);
	        JasperReport report = getFileReport();
		    JasperPrint print = fillReport(map, report,dataReport);
		    output = JasperExportManager.exportReportToPdf(print);
		} catch (JRException e) {
			LOGGER.error(e.getMessage());	
		} catch (Exception e) {
			LOGGER.error(e.getMessage());	
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