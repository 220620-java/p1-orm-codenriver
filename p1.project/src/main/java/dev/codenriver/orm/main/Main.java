package dev.codenriver.orm.main;

import dev.cooley.orm.Data.DAO;
import dev.cooley.orm.models.Message;

public class Main {

	public static void main(String[] args) {
		Message message = new Message(200, "11/23/1996", "Welcome to the world.", 1);
		DAO dao = new DAO();
		dao.storeObject(message, "test_db.messages");

	}

}
