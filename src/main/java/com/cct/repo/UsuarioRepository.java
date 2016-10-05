package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cct.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
