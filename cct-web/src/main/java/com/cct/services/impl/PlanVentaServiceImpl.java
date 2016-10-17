package com.cct.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.PlanVenta;
import com.cct.redis.RedisHolder;
import com.cct.repo.PlanVentaRepository;
import com.cct.services.PlanVentaService;

@Service
public class PlanVentaServiceImpl implements PlanVentaService {
	
	private static final String PLAN_VENTA_PREFIX = "PV";
	
	@Autowired
	private PlanVentaRepository planVentaRepository;
	
	@Autowired
	private RedisHolder<PlanVenta> planVentaHolder;
	
	@Override
	public PlanVenta crearPlan(PlanVenta planVenta) {
		return planVentaRepository.save(planVenta);
	}

	@Override
	public PlanVenta obtenerPlan(Long idPlanVenta) {
		String key = PLAN_VENTA_PREFIX + idPlanVenta;
		PlanVenta planVenta = planVentaHolder.get(key, PlanVenta.class);
		if(planVenta == null){
			planVenta = planVentaRepository.findOne(idPlanVenta);
			planVentaHolder.add(planVenta, key);
		}
		return planVenta;
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
