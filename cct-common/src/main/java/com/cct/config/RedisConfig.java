package com.cct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	@Bean
	RedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.setHostName(System.getenv("REDIS_HOSTNAME"));
		jedisConnectionFactory.setPort(Integer.parseInt(System.getenv("REDIS_PORT")));
		if(System.getenv().containsKey("REDIS_PASSWORD")){
			jedisConnectionFactory.setPassword(System.getenv("REDIS_PASSWORD"));
		}
		return jedisConnectionFactory;
	}

	@Bean
    RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

}
