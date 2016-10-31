package com.cct.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisClient {

	@Resource(name="redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * The logger object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisClient.class);
		
	/**
	 * Expiration time
	 */
	private long time = 900;
	
	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * Contains key.
	 *
	 * @param id the id
	 * @return true if redis contains a mapping for the specified key
	 */
	public boolean containsKey(String id) {
		if(id == null) {
			throw new IllegalArgumentException("NULL id sent");
		}
		return (redisTemplate.opsForValue().get(id) != null);
	}


	/**
	 * Method that puts a object in redis.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void put(String key, String value) {
		if(key == null) {
			throw new IllegalArgumentException("NULL id sent");
		}
		LOGGER.info("Storing object in Redis with id {}", key);
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * Method that puts a object in redis with a specific timeout.
	 *
	 * @param key the key
	 * @param value the value
	 * @param timeOut timeout in seconds
	 */
	public void put(String key, String value, long timeOut) {
		if(key == null) {
			throw new IllegalArgumentException("NULL id sent");
		}
		LOGGER.info("Storing value in Redis with key {}", key);
		redisTemplate.opsForValue().set(key, value, timeOut, TimeUnit.SECONDS);
	}

	/**
	 * Obtains an object from Redis.
	 *
	 * @param id key of the object
	 * @return null if object was not found with the specified key
	 */
	public Object get(String id) {
		if(id == null) {
			throw new IllegalArgumentException("NULL id sent");
		}
		LOGGER.info("Load object from Redis with id {}", id);
		return redisTemplate.opsForValue().get(id);
	}
	
	/**
	 * Remove object from redis.
	 *
	 * @param id key of the object to be removed
	 */
	public void remove(String id) {
		redisTemplate.delete(id);
	}
	
}
