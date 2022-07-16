package dev.cooley.orm.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import dev.cooley.orm.models.Message;
import dev.cooley.orm.util.Json;  

public class Main {

	
	@SuppressWarnings("unchecked")
	public static <O> void main(String[] args) throws IllegalArgumentException, IllegalAccessException, JsonProcessingException, NoSuchMethodException, SecurityException, InstantiationException, InvocationTargetException, ClassNotFoundException {
		@SuppressWarnings("rawtypes")
		DAO dao = new DAO();
		Message message = new Message();
		
		ArrayList<?> allMessages = dao.getTable(message.getClass(), "test_db.messages");
		for (int i=0; i<allMessages.size(); i++) {
			
			System.out.println(Json.nodeToString(Json.toJson(allMessages.get(i))));
		}
		
		}
	}
