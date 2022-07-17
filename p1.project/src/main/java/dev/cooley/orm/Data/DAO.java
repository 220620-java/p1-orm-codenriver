package dev.cooley.orm.Data;

import java.lang.reflect.Constructor; 
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dev.codenriver.orm.annotations.*;
import dev.codenriver.orm.annotations.BasicConstructor;
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
		// TODO:
		String sql = "insert into " + table;
		String columns = " (";
		String values = "values (";
		try {
			Connection conn = connUtil.openConnection();
			
			conn.setAutoCommit(false);
			
			Class classObj = obj.getClass();
			Field[] fields = classObj.getDeclaredFields();
			int count = 0;
			for (Field field: fields) {
				field.setAccessible(true);
				count += 1;
				if (count < fields.length) {
					columns += field.getName() + ", ";
					if (field.getType().isPrimitive()) {
						values += field.get(obj) + ", ";
					} else {
						values += "'"+field.get(obj)+"'" + ", ";
					}
				} else {
					columns += field.getName() + ") "; 	
					values += field.get(obj) + ");";
				}
			} 
			sql += columns + values;
			
			PreparedStatement state = conn.prepareStatement(sql);
			int rowsAffected = state.executeUpdate();
			
			if (rowsAffected == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			logger.log(e.toString());
		}
		
	}

	@Override
	public void deleteObject(Object obj, String table) {
		try {
			Connection conn = connUtil.openConnection();
			Field[] fields = obj.getClass().getDeclaredFields();
			String primaryKey = null;
			int primaryKeyValue = 0;
			for (Field field: fields) {
				if (field.isAnnotationPresent(PrimaryKey.class)) {
					primaryKey = field.getName();
					primaryKeyValue = field.getInt(obj);
				}
			}
			
			String sql = "delete from "+ table +" where "+ primaryKey +"="+ primaryKeyValue +";";
			PreparedStatement state = conn.prepareStatement(sql); 
			state.execute();
			
		} catch (Exception e) {
			logger.log(e.toString());
		}
		
	}
	
	@Override
	public Object getByField(String fieldKey, Object obj, String table) throws IllegalArgumentException, IllegalAccessException {
		
		Class clsObj = obj.getClass();
		Field[] objFields = clsObj.getDeclaredFields();
		Object fieldValue = null;
		for (Field field: objFields) {
			if (field.isAnnotationPresent(PrimaryKey.class)) {
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
	
	public ArrayList<Object> getTable(Class obj, String table) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArrayList<Object> allRows = new ArrayList<>(5);
		String name = obj.getName();
		Class<?> classObj = Class.forName(name);
		Constructor[] constructors = classObj.getConstructors();
		
		try {
			Connection conn = connUtil.openConnection();
			
			String sql = "Select * from " + table;
			PreparedStatement state = conn.prepareStatement(sql);
			ResultSet resultSet = state.executeQuery();
			
			while (resultSet.next()) {
				for (Constructor construct: constructors) {
					if (construct.isAnnotationPresent(BasicConstructor.class)) {
						Object temp = construct.newInstance();
						Class tempCls = temp.getClass();
						
						Field[] fields = tempCls.getDeclaredFields();
						for (Field field: fields) {
							field.setAccessible(true);
							field.set(temp, resultSet.getObject(field.getName()));
							
						}
						allRows.add(temp);
					}
				}
				
				
			}
			
			} catch(Exception e) {
				logger.log(e.toString() + e.getStackTrace());
			}
		return allRows;
	}
}
