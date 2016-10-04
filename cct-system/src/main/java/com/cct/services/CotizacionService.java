package com.cct.services;

import java.util.Collection;

import com.cct.model.Cotizacion;

public interface CotizacionService {
	
	Cotizacion crearCotizacion(Cotizacion cotizacion);
	
	Cotizacion obtenerCotizacion(Long idCotizacion);
	
	Collection<Cotizacion> obtenerCotizaciones();
	
	Cotizacion actualizarCotizacion(Cotizacion cotizacion);
	
	void eliminarCotizacion(Long idCotizacion);

}
