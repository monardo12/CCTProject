package com.cct.services;

import java.util.List;

import com.cct.model.Inventario;

public interface InventarioService {
	
	Inventario crearInventario(Inventario inventario);
	
	Inventario obtenerInventario(Long idInventario);
	
	List<Inventario> obtenerInventarios();
	
	Inventario actualizarInventario(Inventario inventario);
	
	void eliminarInventario(Long idInventario);

}
