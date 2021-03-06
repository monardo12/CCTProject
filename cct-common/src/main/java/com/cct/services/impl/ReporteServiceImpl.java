package com.cct.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.Reporte;
import com.cct.repo.ReporteRepository;
import com.cct.services.ReporteService;

@Service
public class ReporteServiceImpl implements ReporteService {

	@Autowired
	private ReporteRepository reporteRepository;

	@Override
	public Reporte crearReporte(Reporte reporte) {
		reporte.setCreationDate(new Date());
		return reporteRepository.save(reporte);
	}

	@Override
	public Reporte obtenerReporte(Long idReporte) {
		return reporteRepository.findOne(idReporte);
	}

	@Override
	public List<Reporte> obtenerReportes() {
		return reporteRepository.findAll();
	}
	
	@Override
	public List<Reporte> obtenerReportesSinProcesar() {
		return reporteRepository.findByUrl(null);
	}

	@Override
	public Reporte actualizarReporte(Reporte reporte) {
		Reporte reporteActual = reporteRepository.findOne(reporte.getIdReporte());
		reporteActual.setEstado(reporte.getEstado());
		reporteActual.setUrl(reporte.getUrl());
		reporteActual.setMd5(reporte.getMd5());
		return reporteRepository.save(reporte);
	}

	@Override
	public void eliminarReporte(Long idReporte) {
		reporteRepository.delete(idReporte);
	}

}
