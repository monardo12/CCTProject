package com.cct.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cct.model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long>{

	public List<Inventario> findAllInventarioByFechacompraGreaterThanEqualAndFechacompraLessThanEqual(Date fechaInicial, Date fechaFinal);
	
}
