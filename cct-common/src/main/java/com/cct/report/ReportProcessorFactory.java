package com.cct.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.cct.constant.TipoReporte;
import com.cct.report.AbstractReportProcessor;
import com.cct.report.InventarioReportProcessor;

@Component
public class ReportProcessorFactory {

	@Autowired
	private ApplicationContext applicationContext;
	
	public AbstractReportProcessor<?> getReportProcessor(TipoReporte tipoReporte){
		return applicationContext.getBean(InventarioReportProcessor.class);
	}
	
}
