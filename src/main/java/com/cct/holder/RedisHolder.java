package com.cct.holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cct.redis.RedisClient;
import com.cct.util.ObjectSerializer;

@Component
public class RedisHolder<E> {

	@Autowired
	private RedisClient redisClient;
	
	private ObjectSerializer<E> serializer = new ObjectSerializer<E>();
	
	public void add(E object, String key){
		String json = serializer.serializeObject(object);
		redisClient.put(key, json);
	}
	
	public E get(String key, Class<E> _class){
		String json = (String) redisClient.get(key);
		if(json == null){
			return null;
		}
		return serializer.deserializeObject(json, _class);
	}
	
}
