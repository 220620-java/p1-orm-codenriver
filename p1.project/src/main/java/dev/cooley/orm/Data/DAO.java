package dev.cooley.orm.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dev.cooley.orm.util.ConnectionUtil;
import dev.cooley.orm.util.Logger;

@SuppressWarnings({"rawtypes"})
public class DAO <O> implements DataAccessObject {
	
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil("database.properties");
	private Logger logger = Logger.getLogger();

	
	@Override
	public void updateObject(String primaryKey, int pKeyIndex, Object obj, String table) {
		try { 
			Connection conn = connUtil.openConnection();
			
			
			Class clasObj = obj.getClass();
			Field[] objFields = clasObj.getDeclaredFields();
			for (Field field: objFields) {
				field.setAccessible(true);
				if (field.getType().isPrimitive()) {
					// update table set fieldName = 'fieldValue' where primaryKey=pKeyIndex;
					String sql = "update "+ table +" set "+ field.getName() +" = "+ field.get(obj) +" where "+ primaryKey +" = "+ pKeyIndex +";";
					PreparedStatement state = conn.prepareStatement(sql);
					state.executeUpdate();
				} else {
					String sql = "update "+ table +" set "+ field.getName() +" = '"+ field.get(obj)  +"' where "+ primaryKey +"="+ pKeyIndex +";";
					PreparedStatement state = conn.prepareStatement(sql);
					state.executeUpdate();
				}
			}
		} catch (Exception e) {
			logger.log(e.toString());
		}
	}

	@Override
	public void storeObject(Object obj, String table) {
		//String sql = "insert into messages (messageid, likes, postdate) values (default, 200, '2022-07-01');";
		
	}

	@Override
	public void deleteObject(Object obj, String table) {
		
		
	}
	
	@Override
	public Object getByField(String fieldKey, Object obj, String table) throws IllegalArgumentException, IllegalAccessException {
		
		Class clsObj = obj.getClass();
		Field[] objFields = clsObj.getDeclaredFields();
		Object fieldValue = null;
		for (Field field: objFields) {
			if (field.getName() == fieldKey) {
				field.setAccessible(true);
				fieldValue = field.get(obj);
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
	
	public ArrayList<Object> getTable(Object obj, String table) {
		ArrayList<Object> allRows = new ArrayList<>(5);
		
		try {
			Connection conn = connUtil.openConnection();
			
			String sql = "Select * from " + table;
			PreparedStatement state = conn.prepareStatement(sql);
			ResultSet resultSet = state.executeQuery(sql);
			
			while (resultSet.next()) {
				Class clsObj = obj.getClass();
				Constructor[] constructs = clsObj.getConstructors();
				for (Constructor struct: constructs) {
					Object newObj =  struct.newInstance();
					
				}
				
			}
			
			} catch(Exception e) {
			
		}
		return null;
	}
}
