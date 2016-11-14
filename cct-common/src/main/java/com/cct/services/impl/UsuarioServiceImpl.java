package com.cct.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cct.model.Usuario;
import com.cct.repo.UsuarioRepository;
import com.cct.security.UserRole;
import com.cct.services.UsuarioService;
import com.cct.util.PublicKeyGenerator;
import com.cct.util.RandomGeneratorUtil;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Usuario crearUsuario(Usuario usuario) {
		String privateKey = RandomGeneratorUtil.generateApiKey();
		String privateLogin = RandomGeneratorUtil.generateApiLogin();
		usuario.setPrivateKey(privateKey);
		usuario.setPrivateLogin(privateLogin);
		usuario.setRole(UserRole.USER);
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		Usuario newUser = usuarioRepository.save(usuario);
		
		String publicKey = PublicKeyGenerator.create(newUser.getIdUsuario());
		newUser.setPublicKey(publicKey);
		
		return usuarioRepository.save(newUser);
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
