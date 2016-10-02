package com.cct.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.Cliente;
import com.cct.repo.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findById(Long idCliente){
		return clienteRepository.findOne(idCliente);
	}
	
}
