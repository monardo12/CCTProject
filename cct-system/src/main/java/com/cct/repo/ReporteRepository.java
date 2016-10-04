package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cct.model.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long>{

}
