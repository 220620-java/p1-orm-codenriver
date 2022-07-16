package dev.codenriver.orm.data;

import static org.junit.jupiter.api.Assertions.assertEquals; 

import org.junit.jupiter.api.Test;

import dev.cooley.orm.Data.DAO;
import dev.cooley.orm.models.Message;

public class DAOTest {

	@SuppressWarnings("rawtypes")
	@Test
	void getByField() throws IllegalArgumentException, IllegalAccessException {
		Message testObj = new Message(1);
		DAO newdao = new DAO();
		newdao.getByField("messageid", testObj, "test_db.messages");
		assertEquals(1815, testObj.getLikes());
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	void update() throws IllegalArgumentException, IllegalAccessException {
		Message testObj = new Message(2, "5/7/2022", "Hey this is a test.", 400);
		Message checkObj = new Message(2);
		DAO newdao = new DAO();
		newdao.updateObject("messageid", 2, testObj, "test_db.messages");
		newdao.getByField("messageid", checkObj, "test_db.messages");
		assertEquals("Hey this is a test.", checkObj.getContents());
	}
} 
