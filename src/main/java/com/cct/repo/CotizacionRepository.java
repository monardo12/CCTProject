package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cct.model.Cotizacion;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Long>{

	Cotizacion findByIdCotizacion(Long idCotizacion);

}
