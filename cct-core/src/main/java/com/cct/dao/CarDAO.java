package com.cct.dao;

import java.util.List;

import javax.sql.DataSource;
import com.cct.model.Car;

public interface CarDAO {
	
	/** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    */
   public void setDataSource(DataSource ds);
   
   /** 
    * This is the method to be used to create
    * a record in the Car table.
    */
   public void create(Integer id, String name, Integer price);
   
   /** 
    * This is the method to be used to list down
    * a record from the Car table corresponding
    * to a passed car id.
    */
   public Car getCar(Integer id);
   /** 
    * This is the method to be used to list down
    * all the records from the Car table.
    */
   public List<Car> listCars();
   /** 
    * This is the method to be used to delete
    * a record from the Car table corresponding
    * to a passed car id.
    */
   public void delete(Integer id);
   /** 
    * This is the method to be used to update
    * a record into the Car table.
    */
   public void update(Integer id, Integer price);

}
