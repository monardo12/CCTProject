package com.cct.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.Cliente;
import com.cct.redis.RedisHolder;
import com.cct.repo.ClienteRepository;
import com.cct.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private static final String CLIENTE_PREFIX = "C";
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RedisHolder<Cliente> clienteHolder;

	@Override
	public Cliente crearCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente obtenerCliente(Long idCliente) {
		String key = CLIENTE_PREFIX + idCliente;
		Cliente cliente = clienteHolder.get(key, Cliente.class);
		if(cliente == null){
			cliente = clienteRepository.findOne(idCliente);
			clienteHolder.add(cliente, key);
		}
		return cliente;
	}

	@Override
	public Collection<Cliente> obtenerClientes() {
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
