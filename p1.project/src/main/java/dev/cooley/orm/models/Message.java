package dev.cooley.orm.models;

import java.util.Objects;

public class Message {
	private Integer messageid;
	private String postdate;
	private String contents;
	private Integer likes;
	
	public Message(int id) {
		this.messageid = id;
		this.postdate = "";
		this.contents = "";
		this.likes = 0;
	}

	public Message(int id, String postdate, String contents , int likes) {
		this.messageid = id;
		this.postdate = postdate;
		this.contents = contents;
		this.likes = likes;
	}

	public int getId() {
		return messageid;
	}

	public void setId(int id) {
		this.messageid = id;
	}
	
	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Override
	public int hashCode() {
		
		return Objects.hash(messageid, postdate, contents, likes);
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
		return Objects.equals(postdate, other.postdate) && messageid == other.messageid && likes == other.likes && contents == other.contents;
	}

	@Override
	public String toString() {
		return "Message [id=" + messageid + ", date=" + postdate + "Post= " + contents + ", likes=" + likes + "]";
		
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPostDate() {
		return postdate;
	}

	public void setPostDate(String postDate) {
		this.postdate = postDate;
	}
	
}