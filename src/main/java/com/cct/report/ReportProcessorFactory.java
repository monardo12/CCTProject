package com.cct.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.cct.constant.TipoReporte;

@Component
public class ReportProcessorFactory {

	@Autowired
	private ApplicationContext applicationContext;
	
	public AbstractReportProcessor<?> getReportProcessor(TipoReporte tipoReporte){
		switch(tipoReporte){
			case INVENTARIO:
				return applicationContext.getBean(InventarioReportProcessor.class);
			default:
				return null;
		}
	}
	
}
