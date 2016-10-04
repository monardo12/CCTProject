package com.cct.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.Cliente;
import com.cct.repo.ClienteRepository;
import com.cct.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente crearCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente obtenerCliente(Long idCliente) {
		return clienteRepository.findByidcliente(idCliente);
	}

	@Override
	public Collection<Cliente> obtenerClientees() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente actualizarCliente(Cliente cliente) {
		return clienteRepository.saveAndFlush(cliente);
	}

	@Override
	public void eliminarCliente(Long idCliente) {
		clienteRepository.delete(idCliente);	
	}

}
