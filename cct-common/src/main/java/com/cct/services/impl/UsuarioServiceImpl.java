package com.cct.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.Usuario;
import com.cct.repo.UsuarioRepository;
import com.cct.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired UsuarioRepository usuarioRepository;

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario obtenerUsuario(Long idUsuario) {
		return usuarioRepository.findOne(idUsuario);
	}

	@Override
	public Collection<Usuario> obtenerUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario actualizarCliente(Usuario usuario) {
		return usuarioRepository.saveAndFlush(usuario);
	}

	@Override
	public void eliminarUsuario(Long idUsuario) {
		usuarioRepository.delete(idUsuario);
	}

}
