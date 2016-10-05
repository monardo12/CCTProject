package com.cct.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.Servicio;
import com.cct.repo.ServicioRepository;
import com.cct.services.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService {
	
	@Autowired
	private ServicioRepository servicioRepository;

	@Override
	public Servicio crearServicio(Servicio servicio) {
		return servicioRepository.save(servicio);
	}

	@Override
	public Servicio obtenerServicio(Long idServicio) {
		return servicioRepository.findByIdServicio(idServicio);
	}

	@Override
	public Collection<Servicio> obtenerServicios() {
		return servicioRepository.findAll();
	}

	@Override
	public Servicio actualizarServicio(Servicio servicio) {
		return servicioRepository.saveAndFlush(servicio);
	}

	@Override
	public void eliminarServicio(Long idServicio) {
		servicioRepository.delete(idServicio);	
	}

}
