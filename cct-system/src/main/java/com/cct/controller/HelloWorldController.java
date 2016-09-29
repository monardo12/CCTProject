package com.cct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cct.redis.RedisClient;

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

}
