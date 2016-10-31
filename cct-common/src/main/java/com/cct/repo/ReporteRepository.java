package com.cct.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cct.model.Reporte;

public interface ReporteRepository extends JpaRepository<Reporte, Long>{

	public List<Reporte> findByUrl(String url);
	
}
