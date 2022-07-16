package dev.cooley.orm.Data;


public interface DataAccessObject <O> {
	
	
	void storeObject(O obj, String table);
	
	void deleteObject(O obj, String table);

	Object getByField(String fieldKey, O obj, String table) throws IllegalArgumentException, IllegalAccessException;

	void updateObject(String primaryKey, int pKeyIndex, Object obj, String table);
}
