package com.cct.repo;

import org.springframework.data.repository.CrudRepository;

import com.cct.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

}
