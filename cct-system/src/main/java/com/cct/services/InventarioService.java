package com.cct.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cct.repo.ItemRepository;
import com.cct.repo.UnidadItemRepository;

@Service
public class InventarioService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UnidadItemRepository unidadItemRepository;
	
}
