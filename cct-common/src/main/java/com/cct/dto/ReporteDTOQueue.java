package com.cct.dto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReporteDTOQueue {

	private Map<Long, ReporteDTO> reporteMap = new HashMap<Long, ReporteDTO>();

	public Map<Long, ReporteDTO> getReporteList() {
		return reporteMap;
	}

	public void setReporteList(Map<Long, ReporteDTO> reporteMap) {
		this.reporteMap = reporteMap;
	}

	public void addReporteToQueue(ReporteDTO reporteDTO) {
		this.reporteMap.put(reporteDTO.getId(), reporteDTO);
	}

	public void deleteReporteFromQueue(ReporteDTO reporteDTO) {
		for (Iterator<Map.Entry<Long, ReporteDTO>> it = reporteMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<Long, ReporteDTO> reporte = it.next();
			if (reporte.getKey().equals(reporteDTO.getId())) {
				it.remove();
			}
		}
	}

}
