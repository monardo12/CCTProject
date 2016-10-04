package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cct.model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long>{

	Servicio findByidservicio(Long idServicio);

}
