package dev.cooley.orm.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
	private int id;
	private String date;
	private int likes;
	
	public Message() {
		super();
		this.id = 0;
		this.date = "";
		this.likes = 0;
	}

	public Message(int id, String date, int likes) {
		super();
		this.id = id;
		this.date = date;
		this.likes = likes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, likes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(date, other.date) && id == other.id && likes == other.likes;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", date=" + date + ", likes=" + likes + "]";
	}
	
}