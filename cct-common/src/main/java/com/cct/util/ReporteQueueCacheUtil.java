package com.cct.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cct.dto.ReporteDTO;
import com.cct.dto.ReporteDTOQueue;
import com.cct.redis.RedisHolder;

@Component
public class ReporteQueueCacheUtil {

	@Autowired
	private RedisHolder<ReporteDTOQueue> reporteHolder;
	
	private static final String REPORT_QUEUE_KEY = "ReporteMQ";
	
	public ReporteDTOQueue getReporteQueueCache(){
		return reporteHolder.get(REPORT_QUEUE_KEY, ReporteDTOQueue.class);
	}
	
	public void addReporteToCacheQueue(ReporteDTO reporteDTO){
		ReporteDTOQueue reporteQueue = reporteHolder.get(REPORT_QUEUE_KEY, ReporteDTOQueue.class);
		if(reporteQueue == null){
			reporteQueue = new ReporteDTOQueue();
		}
		reporteQueue.addReporteToQueue(reporteDTO);
		reporteHolder.add(reporteQueue, REPORT_QUEUE_KEY);
	}
	
	public void deleteReporteFromCacheQueue(ReporteDTO reporteDTO){
		ReporteDTOQueue reporteQueue = reporteHolder.get(REPORT_QUEUE_KEY, ReporteDTOQueue.class);
		if(reporteQueue != null){
			reporteQueue.deleteReporteFromQueue(reporteDTO);
			reporteHolder.add(reporteQueue, REPORT_QUEUE_KEY);
		}
	}
	
	public boolean isReporteInCacheQueue(ReporteDTO reporteDTO){
		ReporteDTOQueue reporteQueue = reporteHolder.get(REPORT_QUEUE_KEY, ReporteDTOQueue.class);
		if(reporteQueue != null){
			return reporteQueue.getReporteList().containsKey(reporteDTO.getId());
		}
		return false;
	}
	
}
