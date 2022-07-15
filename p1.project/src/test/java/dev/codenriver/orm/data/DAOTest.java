package dev.codenriver.orm.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dev.cooley.orm.Data.DAO;
import dev.cooley.orm.Data.models.Message;

public class DAOTest {

	@SuppressWarnings("rawtypes")
	@Test
	void getByField() throws IllegalArgumentException, IllegalAccessException {
		Message testObj = new Message();
		DAO newdao = new DAO();
		newdao.getByField("messageid", testObj, "test_db.messages");
		assertEquals(testObj.getLikes(), 200);
	}
} 
