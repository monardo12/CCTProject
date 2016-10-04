package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cct.model.Cotizacion;
import com.cct.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long>{

	Servicio findByidservicio(Long idServicio);
	
}
