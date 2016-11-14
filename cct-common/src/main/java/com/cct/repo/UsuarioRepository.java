package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cct.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
	
	public Usuario findByPrivateKeyAndPrivateLogin(String privateKey, String privateLogin);
	
	public Usuario findByPublicKey(String publicKey);
	
}
