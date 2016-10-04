package com.cct.services;

import java.util.Collection;

import com.cct.model.Item;

public interface ItemService {
	
	Item crearItem(Item item);
	
	Item obtenerItem(Long idItem);
	
	Collection<Item> obtenerItems();
	
	Item actualizarItem(Item item);
	
	void eliminarItem(Long idItem);

}
