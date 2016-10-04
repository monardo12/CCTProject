package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cct.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente findByNombre(String nombre);
	
	Cliente findByidcliente(Long idCliente);
	
}
