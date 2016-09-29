package com.cct.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;

	public int countServicios(){
		return jdbcTemplate.queryForObject("select count(*) from tp_servicio", Integer.class);
	}
}
