package com.cct.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.repo.SocioRepository;

@Service
public class SocioService {

	@Autowired
	private SocioRepository socioRepository;
	
}
