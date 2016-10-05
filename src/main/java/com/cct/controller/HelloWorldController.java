package com.cct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cct.dto.Email;
import com.cct.model.Cliente;
import com.cct.redis.RedisClient;
import com.cct.repo.ClienteRepository;

@Controller
public class HelloWorldController {

	@Autowired
	private RedisClient redisClient;

	@Autowired
	private JmsTemplate jmsTemplate;

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

	@RequestMapping(value = "/message", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> sendMessage(@RequestBody Email email){
		jmsTemplate.convertAndSend("mailbox", email);
		return new ResponseEntity<>("Message Received!", HttpStatus.OK);
	}

}
