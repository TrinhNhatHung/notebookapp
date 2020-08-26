package model;

import java.util.List;

public class NormalUser {
	private int id;
	private String name;
	private String password;
	private List<Note> notebook;
	private List<Note> bin;

	public NormalUser() {
		super();
	}

	public NormalUser(int id,String name, String password, List<Note> notebook, List<Note> bin) {
		super();
		this.id =id;
		this.name = name;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
