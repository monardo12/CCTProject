package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cct.model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long>{

}
