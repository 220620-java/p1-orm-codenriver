package dev.cooley.orm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {
	// TODO: Document methods
	
	private static ObjectMapper objectMapper = createObjectMapper();
	
	/**
	 * 
	 */
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
	
	public static JsonNode toJson(Object classObj) {
		return objectMapper.valueToTree(classObj);
	}
	
	public static String nodeToString(JsonNode node) throws JsonProcessingException {
		return generateString(node, false);
	}
	
	public static String nodeToFormatedString(JsonNode node) throws JsonProcessingException {
		return generateString(node, true);
	}
	
	private static String generateString(JsonNode node, boolean type) throws JsonProcessingException {
		ObjectWriter objectWriter = objectMapper.writer();
		
		if ( type ) {
			objectWriter.with(SerializationFeature.INDENT_OUTPUT);
		}
		return objectWriter.writeValueAsString(node);
		
	}
}
