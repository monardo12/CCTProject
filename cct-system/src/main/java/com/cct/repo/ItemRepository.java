package com.cct.repo;

import org.springframework.data.repository.CrudRepository;

import com.cct.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{

}
