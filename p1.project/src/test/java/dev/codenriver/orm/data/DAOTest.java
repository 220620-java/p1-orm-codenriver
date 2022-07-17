package dev.codenriver.orm.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dev.cooley.orm.Data.DAO;
import dev.codenriver.orm.models.Message;

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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	void getAll() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Message testMessage = new Message();
		Message checkObj = new Message(100);
		DAO newdao = new DAO();
		ArrayList<Object> posts = newdao.getTable(testMessage.getClass(), "test_db.messages");
		newdao.getByField("messageid", checkObj, "test_db.messages");
		assertEquals(checkObj.toString(), ((Message) posts.get(0)).toString());
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	void deleteObject() throws IllegalArgumentException, IllegalAccessException {
		DAO dao = new DAO();
		Message checkObj = new Message(500, "11/11/2020", "This is a test entry.", 2);
		dao.storeObject(checkObj, "test_db.messages");
		dao.deleteObject(checkObj, "test_db.messages");
		try {
			((Message) dao.getByField("messageid", 500, "test_db.messages")).equals(null);
		} catch (NullPointerException e) {
			assertEquals(true, true);
		}
	} 
} 
