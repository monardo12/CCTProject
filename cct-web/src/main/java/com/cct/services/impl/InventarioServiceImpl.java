package com.cct.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.Inventario;
import com.cct.redis.RedisHolder;
import com.cct.repo.InventarioRepository;
import com.cct.services.InventarioService;

@Service
public class InventarioServiceImpl implements InventarioService {
	
	private static final String ALL_INVENTARIO_KEY = "I";
	
	@Autowired
	private InventarioRepository inventarioRepository;
	
	@Autowired
	private RedisHolder<Inventario[]> inventarioHolder;

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
		Inventario[] inventarioArray = inventarioHolder.get(ALL_INVENTARIO_KEY, Inventario[].class);
		if(inventarioArray == null){
			List<Inventario> inventario = inventarioRepository.findAll();
			inventarioArray = new Inventario[inventario.size()];
			inventarioArray = inventario.toArray(inventarioArray);
			inventarioHolder.add(inventarioArray, ALL_INVENTARIO_KEY);
		}
		return Arrays.asList(inventarioArray);
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
