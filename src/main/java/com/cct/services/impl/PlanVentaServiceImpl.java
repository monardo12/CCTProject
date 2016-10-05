package com.cct.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.PlanVenta;
import com.cct.repo.PlanVentaRepository;
import com.cct.services.PlanVentaService;

@Service
public class PlanVentaServiceImpl implements PlanVentaService {
	
	@Autowired
	private PlanVentaRepository planVentaRepository;
	
	@Override
	public PlanVenta crearPlan(PlanVenta planVenta) {
		return planVentaRepository.save(planVenta);
	}

	@Override
	public PlanVenta obtenerPlan(Long idPlanVenta) {
		return planVentaRepository.findOne(idPlanVenta);
	}

	@Override
	public List<PlanVenta> obtenerPlanes() {
		return planVentaRepository.findAll();
	}

	@Override
	public PlanVenta actualizarPlan(PlanVenta planVenta) {
		return planVentaRepository.save(planVenta);
	}

	@Override
	public void eliminarPlanVenta(Long idPlanVenta) {
		planVentaRepository.delete(idPlanVenta);
	}

}
