package com.cct.services;

import java.util.List;

import com.cct.model.PlanVenta;

public interface PlanVentaService {
	
	PlanVenta crearPlan(PlanVenta planVenta);
	
	PlanVenta obtenerPlan(Long idPlanVenta);
	
	List<PlanVenta> obtenerPlanes();
	
	PlanVenta actualizarPlan(PlanVenta planVenta);
	
	void eliminarPlanVenta(Long idPlanVenta);

}
