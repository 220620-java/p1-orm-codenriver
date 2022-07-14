package dev.coole.data;

import java.util.List;
import dev.coole.models.Message;

public interface MessageDAO extends DataAccessObject<Message> {
	public List<Message> findById(int id);
}
