package com.cct.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.Inventario;
import com.cct.repo.InventarioRepository;
import com.cct.services.InventarioService;

@Service
public class InventarioServiceImpl implements InventarioService {
	
	@Autowired
	private InventarioRepository inventarioRepository;

	@Override
	public Inventario crearInventario(Inventario inventario) {
		return inventarioRepository.save(inventario);
	}

	@Override
	public Inventario obtenerInventario(Long idInventario) {
		return inventarioRepository.findOne(idInventario);
	}

	@Override
	public List<Inventario> obtenerInventarios() {
		return inventarioRepository.findAll();
	}

	@Override
	public Inventario actualizarInventario(Inventario inventario) {
		return inventarioRepository.saveAndFlush(inventario);
	}

	@Override
	public void eliminarInventario(Long idInventario) {
		inventarioRepository.delete(idInventario);	
	}

}
