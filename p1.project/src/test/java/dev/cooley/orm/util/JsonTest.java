package dev.cooley.orm.util;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonTest {

	private String testCase = "{ \"name\" : \"Dylan Cooley\" }";
	
	@Test
	void parse() throws IOException {
		
		JsonNode node = Json.parse(testCase);
		assertEquals(node.get("name").asText(), "Dylan Cooley");
	}
	
	@Test
	void toJson() throws JsonProcessingException, IllegalArgumentException {
		
		JsonNode node = Json.parse(testCase);
		JsonTestObject testObj = Json.fromJson(node, JsonTestObject.class);
		
		assertEquals(testObj.getName(), "Dylan Cooley");
	}
}
