package com.cct.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.PlanVentaItem;
import com.cct.repo.PlanVentaRepository;
import com.cct.repo.ServicioPlanVentaRepository;

@Service
public class PlanVentaService {

	@Autowired
	private PlanVentaRepository planVentaRepository;
	
	@Autowired
	private PlanVentaItem planVentaItemRepository;
	
	@Autowired
	private ServicioPlanVentaRepository servicioPlanVentaRepository;
	
}
