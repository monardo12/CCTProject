package com.cct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cct.model.Cliente;
import com.cct.redis.RedisClient;
import com.cct.repo.ClienteRepository;

@Controller
public class HelloWorldController {

	@Autowired
	private RedisClient redisClient;

	@RequestMapping("/")
	@ResponseBody
	public String helloWorld(){
		Object obj = redisClient.get("key");
		if(obj == null){
			redisClient.put("key", "1");
		} else {
			int value = Integer.parseInt((String) obj);
			value++;
			redisClient.put("key", value + "");
		}

		int realValue = Integer.parseInt((String) redisClient.get("key"));

		return "Value: " + realValue;
	}

	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping("/hello")
	@ResponseBody
	public Cliente prueba(){
		return clienteRepository.findOne(1L);
	}

	@RequestMapping("/loaderio-0d6da71e28d78fae023635643fd2e089")
	@ResponseBody
	public String loaderIO() {
		return "loaderio-0d6da71e28d78fae023635643fd2e089";
	}

}
