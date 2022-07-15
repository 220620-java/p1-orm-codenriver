package dev.cooley.orm.Data.models;

import java.util.Objects;

public class Message {
	private Integer messageid;
	//private String postDate;
	private Integer likes;
	
	public Message() {
		this.messageid = 1;
		//this.postDate = "";
		this.likes = 0;
	}

	public Message(int id, String date, int likes) {
		this.messageid = id;
		//this.postDate = date;
		this.likes = likes;
	}

	public int getId() {
		return messageid;
	}

	public void setId(int id) {
		this.messageid = id;
	}

	/*public String getDate() {
		return postDate;
	}

	public void setDate(String date) {
		this.postDate = date;
	}
*/
	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Override
	public int hashCode() {
		//return Objects.hash(postDate, messageid, likes);
		return Objects.hash(messageid, likes);
	}

	
	/*public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		//return Objects.equals(postDate, other.postDate) && messageid == other.messageid && likes == other.likes;
		//return Objects.equals( messageid == other.messageid) && likes == other.likes;
	}
*/
	@Override
	public String toString() {
		//return "Message [id=" + messageid + ", date=" + postDate + ", likes=" + likes + "]";
		return "Message [id=" + messageid + ", date=" + ", likes=" + likes + "]";
	}
	
}