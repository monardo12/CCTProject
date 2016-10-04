package com.cct.services.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.model.Item;
import com.cct.model.PlanVenta;
import com.cct.repo.ItemRepository;
import com.cct.repo.PlanVentaRepository;
import com.cct.services.ItemService;
import com.cct.services.PlanVentaService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item crearItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item obtenerItem(Long idItem) {
		return itemRepository.findByiditem(idItem);
	}

	@Override
	public Collection<Item> obtenerItems() {
		return itemRepository.findAll();
	}

	@Override
	public Item actualizarItem(Item item) {
		return itemRepository.saveAndFlush(item);
	}

	@Override
	public void eliminarItem(Long idItem) {
		itemRepository.delete(idItem);	
	}

}
