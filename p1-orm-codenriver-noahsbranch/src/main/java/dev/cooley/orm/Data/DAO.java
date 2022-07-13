package dev.cooley.orm.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dev.cavazos.utils.ConnectionUtil;

public class DAO implements DataAccessObject{
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public <O> Object getByID(int id) throws Exception {
		ResultSet resulset = null;
		
		try(Connection conn = getConnection();)
		return null;
	}

	@Override
	public void updateObject(Object obj) {
		
		
	}

	@Override
	public void storeObject(Object obj) {
		
		
	}

	@Override
	public void deleteObject(Object obj) {
		
		
	}

}
