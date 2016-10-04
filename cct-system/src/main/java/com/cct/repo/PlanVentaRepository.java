package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cct.model.PlanVenta;

@Repository
public interface PlanVentaRepository extends JpaRepository<PlanVenta, Long>{

}
