package dev.cooley.orm.Data;

public interface DataAccessObject {
	
	<O> Object getByID(int id);
	
	void updateObject(Object obj);
	
	void storeObject(Object obj);
	
	void deleteObject(Object obj);
}
