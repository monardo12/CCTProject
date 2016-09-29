package com.cct.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cct.model.Car;

public class CarMapper implements RowMapper<Car>{
	public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
      Car car = new Car();
      car.setId(rs.getInt("id"));
      car.setName(rs.getString("name"));
      car.setPrice(rs.getInt("price"));
      return car;
   }
}
