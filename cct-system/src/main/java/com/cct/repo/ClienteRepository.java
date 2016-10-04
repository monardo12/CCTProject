package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cct.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente findByNombre(String nombre);

	Cliente findByidcliente(Long idCliente);

}
