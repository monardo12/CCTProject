package com.cct.common;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cct.jdbc.CarJDBCTemplate;
import com.cct.model.Car;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		CarJDBCTemplate carJDBCTemplate = (CarJDBCTemplate) context.getBean("carJDBCTemplate");

		System.out.println("------Records Creation--------");
		carJDBCTemplate.create(6, "Zara", 11);
		carJDBCTemplate.create(7, "Nuha", 2);
		carJDBCTemplate.create(8, "Ayan", 15);

		System.out.println("------Listing Multiple Records--------");
		List<Car> cars = carJDBCTemplate.listCars();
		for (Car record : cars) {
			System.out.print("ID : " + record.getId());
			System.out.print(", Name : " + record.getName());
			System.out.println(", Price : " + record.getPrice());
		}

		System.out.println("----Updating Record with ID = 2 -----");
		carJDBCTemplate.update(2, 20);

		System.out.println("----Listing Record with ID = 2 -----");
		Car car = carJDBCTemplate.getCar(2);
		System.out.print("ID : " + car.getId());
		System.out.print(", Name : " + car.getName());
		System.out.println(", Age : " + car.getPrice());

	}

}
