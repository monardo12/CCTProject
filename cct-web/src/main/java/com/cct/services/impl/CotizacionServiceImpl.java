package com.cct.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.Cotizacion;
import com.cct.model.Item;
import com.cct.repo.CotizacionRepository;
import com.cct.services.CotizacionService;

@Service
public class CotizacionServiceImpl implements CotizacionService {
	
	@Autowired
	private CotizacionRepository cotizacionRepository;

	@Override
	public Cotizacion crearCotizacion(Cotizacion cotizacion) {
		String formatter = new SimpleDateFormat("YYYY/MM/DD").format(cotizacion.getFechaCreacion());
		try {
			cotizacion.setFechaCreacion(new SimpleDateFormat("YYYY/MM/DD").parse(formatter));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cotizacion.setValorItem(((List<Item>)cotizacion.getItems()).get(0).getPrecio());
		cotizacion.setTotal(((List<Item>)cotizacion.getItems()).get(0).getPrecio() * cotizacion.getCantidadItem());		
		return cotizacionRepository.save(cotizacion);
	}

	@Override
	public Cotizacion obtenerCotizacion(Long idCotizacion) {
		return cotizacionRepository.findByIdCotizacion(idCotizacion);
	}

	@Override
	public Collection<Cotizacion> obtenerCotizaciones() {
		return cotizacionRepository.findAll();
	}

	@Override
	public Cotizacion actualizarCotizacion(Cotizacion cotizacion) {
		return cotizacionRepository.saveAndFlush(cotizacion);
	}

	@Override
	public void eliminarCotizacion(Long idCotizacion) {
		cotizacionRepository.delete(idCotizacion);	
	}

}
