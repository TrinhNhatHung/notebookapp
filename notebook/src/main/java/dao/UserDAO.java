package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EmailUser;
import model.NormalUser;
import model.Note;

public class UserDAO {
	private static Connection connection;

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/notebookapp";
		return DriverManager.getConnection(url, "root", "813564");
	}

	public static List<NormalUser> allNormalUser() throws ClassNotFoundException, SQLException {
		List<NormalUser> list = new ArrayList<NormalUser>();
		connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,name,password FROM normaluser";
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			NormalUser user = new NormalUser();
			user.setId(resultSet.getInt("id"));
			user.setName(resultSet.getString("name"));
			user.setPassword(resultSet.getString("password"));
			list.add(user);
		}
		return list;
	}

	public static List<EmailUser> allEmailUser() throws ClassNotFoundException, SQLException {
		List<EmailUser> list = new ArrayList<EmailUser>();
		connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "SELECT id,name,email,image FROM emailuser";
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			EmailUser user = new EmailUser();
			user.setId(resultSet.getInt("id"));
			user.setName(resultSet.getString("name"));
			user.setEmail(resultSet.getString("email"));
			user.setImage(resultSet.getString("image"));
			list.add(user);
		}
		return list;
	}

	public static int insertEmailUser(EmailUser user) {
		try {
			connection = getConnection();
			PreparedStatement statement = null;
			String sql = "INSERT INTO emailuser (name,email, image) VALUES (?,?,?)";
			statement = connection.prepareStatement(sql,new String[]{"id","name","email","image"});
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getImage());
			ResultSet resultSet =null ;
            statement.execute();
			resultSet=statement.getGeneratedKeys();
			int id =0 ;
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			return id;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public static ResultSet querryNormalUserNote(String key) throws ClassNotFoundException, SQLException {
		connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "SELECT note.id,title,content, notetime,isDeleted FROM note inner join normaluser ON note.normaluser_id = normaluser.id WHERE normaluser.name = ?";
		statement = connection.prepareStatement(sql);
		statement.setString(1, key);
		resultSet = statement.executeQuery();
		return resultSet;
	}

	public static ResultSet querryEmailUserNote(String key) throws ClassNotFoundException, SQLException {
		connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "SELECT note.id,title,content, notetime,isDeleted FROM note inner join emailuser ON note.emailuser_id = emailuser.id WHERE emailuser.email = ?";
		statement = connection.prepareStatement(sql);
		statement.setString(1, key);
		resultSet = statement.executeQuery();
		return resultSet;
	}

	public static int insertNoteOfNormalUser(int isDeleted,int idUser,Note note) throws ClassNotFoundException, SQLException {
		connection = getConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO note (title,content,notetime,isDeleted,normaluser_id) VALUES (?,?,?,?,?)";
		statement = connection.prepareStatement(sql, new String[] {"id"});
		statement.setString(1, note.getTitle());
		statement.setString(2, note.getContent());
		statement.setDate(3, note.getTimeNote());
		statement.setInt(4, isDeleted);
		statement.setInt(5, idUser);
		ResultSet resultSet = null;
		statement.execute();
		resultSet = statement.getGeneratedKeys();
		int id=0;
		while (resultSet.next()) {
			id= resultSet.getInt(1);
		}
		return id;
	}
	
	public static int insertNoteOfEmailUser(int isDeleted, int idUser, Note note)
			throws  SQLException, ClassNotFoundException {
		connection = getConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO note (title,content,notetime,isDeleted,emailuser_id) VALUES (?,?,?,?,?)";
		statement = connection.prepareStatement(sql, new String[] {"id"});
		statement.setString(1, note.getTitle());
		statement.setString(2, note.getContent());
		statement.setDate(3, note.getTimeNote());
		statement.setInt(4, isDeleted);
		statement.setInt(5, idUser);
		ResultSet resultSet = null;
		statement.execute();
		resultSet = statement.getGeneratedKeys();
		int id=0;
		while (resultSet.next()) {
			id= resultSet.getInt(1);
		}
		return id;
	}
	
	public static int updateNote(Note note) throws ClassNotFoundException, SQLException {
		connection = getConnection();
		PreparedStatement statement = null;
		String sql ="UPDATE note SET title= ?,content = ?, notetime = ? WHERE (id = ?)";
		statement = connection.prepareStatement(sql,new String[] {"id"});
		statement.setString(1, note.getTitle());
		statement.setString(2, note.getContent());
		statement.setDate(3,note.getTimeNote());
		statement.setInt(4, note.getId());
		statement.execute();
		ResultSet resultSet = statement.getGeneratedKeys();
		int count = 0;
		while (resultSet.next()) {
			count++;
		}
		return count;
	}

	public static int deleteNote(Note note) throws ClassNotFoundException, SQLException {
		connection = getConnection();
		PreparedStatement statement = null;
		String sql ="UPDATE note SET isDeleted = '1' WHERE (id = ?)";
		statement = connection.prepareStatement(sql,new String[] {"id"});
		statement.setInt(1, note.getId());
		statement.execute();
		ResultSet resultSet = statement.getGeneratedKeys();
		int count = 0;
		while (resultSet.next()) {
			count++;
		}
		return count;
	}
	
	public static int deleteBin(Note note) throws ClassNotFoundException, SQLException {
		connection = getConnection();
		PreparedStatement statement = null;
		String sql ="DELETE FROM note where id = ?";
		statement = connection.prepareStatement(sql,new String[] {"id"});
		statement.setInt(1, note.getId());
		statement.execute();
		ResultSet resultSet = statement.getGeneratedKeys();
		int count = 0;
		while (resultSet.next()) {
			count++;
		}
		return count;
	}
}
