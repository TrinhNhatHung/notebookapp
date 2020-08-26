package model;

import java.sql.Date;

public class Note {
	private int id;
	private String title;
	private String content;
	private Date timeNote;
	private boolean isDeleted;

	public Note() {
		super();
	}

	public Note(int id, String title, String content, Date timeNote, boolean isDeleted) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.timeNote = timeNote;
		this.isDeleted = isDeleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimeNote() {
		return timeNote;
	}

	public void setTimeNote(Date timeNote) {
		this.timeNote = timeNote;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
