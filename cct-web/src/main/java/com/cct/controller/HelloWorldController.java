package com.cct.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cct.model.Cliente;
import com.cct.redis.RedisClient;
import com.cct.repo.ClienteRepository;

@Controller
public class HelloWorldController {

	@Autowired
	private RedisClient redisClient;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@RequestMapping("/test")
	@ResponseBody
	public String helloWorld(){
		Object obj = redisClient.get("key");
		if(obj == null){
			redisClient.put("key", "1");
		} else {
			int value = Integer.parseInt((String) obj);
			value++;
			redisClient.put("key", Integer.toString(value));
		}

		int realValue = Integer.parseInt((String) redisClient.get("key"));

		return "Value: " + realValue;
	}
	
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
	
	@RequestMapping("/loaderio-dc44d0b7c768d24ed2d9376d5ff1594b")
	@ResponseBody
	public String loaderIOCarlos() {
		return "loaderio-dc44d0b7c768d24ed2d9376d5ff1594b";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	@ResponseBody
	public String form(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap();
		StringBuilder response = new StringBuilder("Recibido: ");
		for(Map.Entry<String, String[]> param : params.entrySet()){
			response.append(param.getKey())
			.append(":")
			.append(Arrays.toString(param.getValue()))
			.append(",");
		}
		return response.toString();
	}

}
