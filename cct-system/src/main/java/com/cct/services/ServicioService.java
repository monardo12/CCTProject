package com.cct.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.repo.ServicioItemRepository;
import com.cct.repo.ServicioRepository;

@Service
public class ServicioService {

	@Autowired
	private ServicioRepository servicioRepository;
	
	@Autowired
	private ServicioItemRepository servicioItemRepository;
	
}
