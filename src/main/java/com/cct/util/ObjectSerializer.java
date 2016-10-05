package com.cct.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectSerializer<E> {

	private ObjectMapper mapper = new ObjectMapper();
	
	public String serializeObject(E object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException("Object JSON serialization failed", e);
		}
	}
	
	public E deserializeObject(String json, Class<E> _class) {
		try {
			return mapper.readValue(json, _class);
		} catch (Exception e) {
			throw new IllegalStateException("Configuration JSON deserialization failed", e);
		}
	}
	
}
