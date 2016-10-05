package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cct.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	Item findByIdItem(Long idItem);

}
