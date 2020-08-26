package model;

import java.util.List;

public class EmailUser {
	private int id;
	private String name;
	private String email;
	private String image;
	private List<Note> notebook;
	private List<Note> bin;

	public EmailUser() {
		super();
	}

	public EmailUser(int id, String name, String email, String image, List<Note> notebook, List<Note> bin) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.image = image;
		this.notebook = notebook;
		this.bin = bin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Note> getNotebook() {
		return notebook;
	}

	public void setNotebook(List<Note> notebook) {
		this.notebook = notebook;
	}

	public List<Note> getBin() {
		return bin;
	}

	public void setBin(List<Note> bin) {
		this.bin = bin;
	}

}
