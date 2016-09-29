package com.cct.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cct.dao.CarDAO;
import com.cct.mapper.CarMapper;
import com.cct.model.Car;

@Component("carJDBCTemplate")
public class CarJDBCTemplate implements CarDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Autowired
	public CarJDBCTemplate(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public void create(Integer id, String name, Integer price) {
		String SQL = "insert into car (id, name, price) values (?, ?, ?)";

		jdbcTemplateObject.update(SQL, id, name, price);
		System.out.println("Created Record Name = " + name + " Price = " + price);
		return;
	}

	public Car getCar(Integer id) {
		String SQL = "select * from car where id = ?";
		Car car = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new CarMapper());
		return car;
	}

	public List<Car> listCars() {
		String SQL = "select * from car";
		List<Car> cars = jdbcTemplateObject.query(SQL, new CarMapper());
		return cars;
	}

	public void delete(Integer id) {
		String SQL = "delete from car where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;
	}

	public void update(Integer id, Integer price) {
		String SQL = "update car set price = ? where id = ?";
		jdbcTemplateObject.update(SQL, price, id);
		System.out.println("Updated Record with ID = " + id);
		return;
	}

}
