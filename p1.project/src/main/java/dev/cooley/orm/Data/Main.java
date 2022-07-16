package dev.cooley.orm.Data;

import com.fasterxml.jackson.core.JsonProcessingException; 
import com.fasterxml.jackson.databind.JsonNode;

import dev.cooley.orm.models.Message;
import dev.cooley.orm.util.Json;

public class Main {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, JsonProcessingException {
		Message newMessage = new Message(54);
		DAO newdao = new DAO();
		newdao.getByField("messageid", newMessage, "test_db.messages");
		JsonNode json= Json.toJson(newMessage);
		String jsonString = Json.nodeToFormatedString(json);
		System.out.println(jsonString);
	}

}
