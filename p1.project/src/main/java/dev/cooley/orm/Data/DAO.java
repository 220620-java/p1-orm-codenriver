package dev.cooley.orm.Data;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dev.cooley.orm.util.ConnectionUtil;
import dev.cooley.orm.util.Logger;

@SuppressWarnings({"unchecked", "rawtypes"})
public class DAO <O> implements DataAccessObject {
	
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil("database.properties");
	private Logger logger = Logger.getLogger();

	@Override
	public void updateObject(Object obj, String table) {
		try {
			Connection conn = connUtil.openConnection();
			
			Object fieldValue;
			Class clasObj = obj.getClass();
			Field[] objFields = clasObj.getDeclaredFields();
			for (Field field: objFields) {
			
			String sql = "update "
						+ table
						+ " set " 
						+ field.getName() ;
			}
		} catch (Exception e) {
			logger.log(e.toString());
		}
	}

	@Override
	public void storeObject(Object obj, String table) {
		String sql = "insert into messages (messageid, likes, postdate) values (default, 200, '2022-07-01');";
		
	}

	@Override
	public void deleteObject(Object obj, String table) {
		
		
	}
	
	@Override
	public Object getByField(String fieldKey, Object obj, String table) throws IllegalArgumentException, IllegalAccessException {
		
		Object fieldValue;
		Class clsObj = obj.getClass();
		Field[] objFields = clsObj.getDeclaredFields();
		
		for (Field field: objFields) {
			if (field.getName() == fieldKey) {
				field.setAccessible(true);
				fieldValue = (O) field.get(obj);
				try {
					
					Connection conn = connUtil.openConnection();
					
					String sql = "select * "
							+ "from " 
							+ table
							+ " where " + fieldKey + " = " + fieldValue + ";";
					
					PreparedStatement state = conn.prepareStatement(sql);
					ResultSet resultSet = state.executeQuery();
					
					if (resultSet.next()) {
						System.out.println(resultSet.toString());
						for (Field field1: objFields) {
							field1.setAccessible(true);
							field1.set(obj, resultSet.getObject(field1.getName()));
						}
					}
					return obj;
					
					} catch(Exception e) {
						logger.log(e.toString());
					}
			}
		}
		
		return null;
	}


}
