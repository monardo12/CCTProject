package com.cct.services;

import java.util.Collection;

import com.cct.model.Usuario;

public interface UsuarioService {
	
	Usuario crearUsuario(Usuario usuario);
	
	Usuario obtenerUsuario(Long idUsuario);
	
	Collection<Usuario> obtenerUsuarios();
	
	Usuario actualizarCliente(Usuario usuario);
	
	void eliminarUsuario(Long idUsuario);

}
