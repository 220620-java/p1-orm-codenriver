package dev.cooley.orm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
	
	private static ObjectMapper objectMapper = createObjectMapper();
	
	private static ObjectMapper createObjectMapper() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}
	
	public static JsonNode parse(String js) throws JsonMappingException, JsonProcessingException {
		return objectMapper.readTree(js);
	}
	
	public static <O> O fromJson(JsonNode node, Class<O> classObj) throws JsonProcessingException, IllegalArgumentException {
		return objectMapper.treeToValue(node, classObj);
	}
	
}
