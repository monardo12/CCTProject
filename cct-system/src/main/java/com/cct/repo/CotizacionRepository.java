package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cct.model.Cliente;
import com.cct.model.Cotizacion;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Long>{
	
	Cotizacion findByIdCotizacion(Long idCotizacion);
	
}
