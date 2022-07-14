package dev.coole.data;

import dev.coole.models.User;

public interface UserDAO extends DataAccessObject<User>{
	public User findByUsername(String username);
}
