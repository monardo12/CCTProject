package com.cct.services;

import java.util.List;

import com.cct.model.Reporte;

public interface ReporteService {

	Reporte crearReporte(Reporte reporte);

	Reporte obtenerReporte(Long idReporte);

	List<Reporte> obtenerReportes();
	
	List<Reporte> obtenerReportesSinProcesar();

	Reporte actualizarReporte(Reporte reporte);

	void eliminarReporte(Long idReporte);

}
