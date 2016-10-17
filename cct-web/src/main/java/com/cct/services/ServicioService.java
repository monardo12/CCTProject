package com.cct.services;

import java.util.Collection;

import com.cct.model.Servicio;

public interface ServicioService {
	
	Servicio crearServicio(Servicio servicio);
	
	Servicio obtenerServicio(Long idServicio);
	
	Collection<Servicio> obtenerServicios();
	
	Servicio actualizarServicio(Servicio servicio);
	
	void eliminarServicio(Long idServicio);

}
