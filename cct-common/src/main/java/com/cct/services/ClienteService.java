package com.cct.services;

import java.util.Collection;

import com.cct.model.Cliente;

public interface ClienteService {
	
	Cliente crearCliente(Cliente cliente);
	
	Cliente obtenerCliente(Long idCliente);
	
	Collection<Cliente> obtenerClientes();
	
	Cliente actualizarCliente(Cliente cliente);
	
	void eliminarCliente(Long idCliente);

}
